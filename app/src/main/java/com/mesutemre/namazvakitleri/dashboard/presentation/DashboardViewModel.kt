package com.mesutemre.namazvakitleri.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.dashboard.domain.use_case.GetSelectedDistrictFromDataStore
import com.mesutemre.namazvakitleri.dashboard.domain.use_case.GetTarihteBugunList
import com.mesutemre.namazvakitleri.dashboard.domain.use_case.GetVakitInfoUseCase
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetDailyAyet
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetDailyHadis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getVakitInfoUseCase: GetVakitInfoUseCase,
    private val getDailyHadis: GetDailyHadis,
    private val getSelectedDistrictFromDataStore: GetSelectedDistrictFromDataStore,
    private val getDailyAyet: GetDailyAyet,
    private val getTarihteBugunList: GetTarihteBugunList
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state

    init {
        viewModelScope.launch {
            async {
                getDailyHadis().collectLatest { response ->
                    _state.update {
                        it.copy(
                            gunlukHadis = response
                        )
                    }
                }
            }
            async {
                getDailyAyet().collectLatest { response ->
                    _state.update {
                        it.copy(
                            gunlukAyet = response
                        )
                    }
                }
            }
            async {
                _state.update {
                    it.copy(
                        selectedDistrict = getSelectedDistrictFromDataStore()
                    )
                }
            }
            async {
                getVakitInfoUseCase().collectLatest { response ->
                    _state.update {
                        it.copy(
                            vakitInfo = response
                        )
                    }
                }
            }
            async {
                getTarihteBugunList().collectLatest { response ->
                    _state.update {
                        it.copy(
                            tarihteBugunList = response
                        )
                    }
                }
            }
        }
    }
}