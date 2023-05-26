package com.mesutemre.namazvakitleri.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.domain.model.DashboardVakitPageType
import com.mesutemre.namazvakitleri.dashboard.domain.use_case.*
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetDailyAyet
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetDailyHadis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getVakitInfoUseCase: GetVakitInfoUseCase,
    private val getDailyHadis: GetDailyHadis,
    private val getSelectedDistrictFromDataStore: GetSelectedDistrictFromDataStore,
    private val getDailyAyet: GetDailyAyet,
    private val getTarihteBugunList: GetTarihteBugunList,
    private val getActiveVakitPage: GetActiveVakitPage,
    private val saveActiveVakitPage: SaveActiveVakitPage,
    private val getSonrakiVakit: GetSonrakiVakit,
    private val getCurrentVakit: GetCurrentVakit
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state

    init {
        viewModelScope.launch {
            async {
                loadActiveVakitPage()
            }
            async {
                loadDailyHadis()
            }
            async {
                loadDailyAyet()
            }
            async {
                loadSelectedDistrict()
            }
            async {
                loadVakitInfo()
            }
            async {
                loadTarihteBugun()
            }
        }
    }

    suspend fun loadDailyHadis() {
        getDailyHadis().collectLatest { response ->
            _state.update {
                it.copy(
                    gunlukHadis = response
                )
            }
        }
    }

    suspend fun loadDailyAyet() {
        getDailyAyet().collectLatest { response ->
            _state.update {
                it.copy(
                    gunlukAyet = response
                )
            }
        }
    }

    suspend fun loadSelectedDistrict() {
        _state.update {
            it.copy(
                selectedDistrict = getSelectedDistrictFromDataStore()
            )
        }
    }

    suspend fun loadVakitInfo() {
        getVakitInfoUseCase().collectLatest { response ->
            var kalanSure = 0L
            if (response is BaseResourceEvent.Success) {
                response.data?.let { data ->
                    var timer = Calendar.getInstance().timeInMillis
                    while (timer > 0) {
                        timer -= 1000L
                        var time = (getSonrakiVakit(
                            data,
                            timer
                        )).minus(Calendar.getInstance().timeInMillis)
                        kalanSure = time
                        delay(1000)
                        time -= 1000L
                        _state.update {
                            it.copy(
                                vakitInfo = response,
                                kalanSaat = (kalanSure % Constants.DashboardConstants.DAY_MIL_SEC / Constants.DashboardConstants.HOUR_MIL_SEC).toInt(),
                                kalanDakika = (kalanSure % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC / Constants.DashboardConstants.MIN_MIL_SEC).toInt(),
                                kalanSaniye = (kalanSure % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC % Constants.DashboardConstants.MIN_MIL_SEC / Constants.DashboardConstants.SEC_MIL_SEC).toInt(),
                                currentVakit = getCurrentVakit(data, timer)
                            )
                        }
                    }
                }
            }
        }
    }

    suspend fun loadTarihteBugun() {
        getTarihteBugunList().collectLatest { response ->
            _state.update {
                it.copy(
                    tarihteBugunList = response
                )
            }
        }
    }

    suspend fun loadActiveVakitPage() {
        _state.update {
            it.copy(
                activeVakitPage = getActiveVakitPage()
            )
        }
    }

    suspend fun setActiveVakitPage(type: DashboardVakitPageType) {
        _state.update {
            it.copy(
                activeVakitPage = type
            )
        }
        saveActiveVakitPage(type)
    }
}