package com.mesutemre.namazvakitleri.onboarding.presentation.complete

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingCompleteViewModel @Inject constructor(
    private val saveHadisList: SaveHadisList,
    private val savedStateHandle: SavedStateHandle,
    private val getDistrictDataByDistrictId: GetDistrictDataByDistrictId,
    private val saveSelectedDistrictToDataStore: SaveSelectedDistrictToDataStore,
    private val saveAyetList: SaveAyetList,
    private val checkAnyLocationVakitExist: CheckAnyLocationExist,
    private val clearVakitInfo: ClearVakitInfo
) : ViewModel() {

    val districtId = savedStateHandle.get<String>("districtId")

    private val _state = MutableStateFlow(OnboardingCompleteState())
    val state: StateFlow<OnboardingCompleteState> = _state

    fun saveHadisListWithJson(jsonHadis: String, jsonAyet: String) {
        viewModelScope.launch {
            if (checkAnyLocationVakitExist()) {
                getAndSaveDistrict(isChangeLocation = true)
            } else {
                async { saveHadisList(jsonHadis) }
                async { saveAyetList(jsonAyet) }
                async {
                    getAndSaveDistrict()
                }
            }

        }
    }

    private suspend fun getAndSaveDistrict(isChangeLocation: Boolean = false) {
        getDistrictDataByDistrictId(districtId?.toInt() ?: 0).collectLatest { response ->
            if (response is BaseResourceEvent.Success) {
                saveSelectedDistrictToDataStore(
                    response.data ?: DistrictData(
                        districtId = 0,
                        districtName = ""
                    )
                )
                if (isChangeLocation) {
                    clearVakitInfo()
                }
            }
            _state.update {
                it.copy(
                    districtData = response
                )
            }
        }
    }
}