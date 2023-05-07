package com.mesutemre.namazvakitleri.onboarding.presentation.complete

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetDistrictDataByDistrictId
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.SaveHadisList
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.SaveSelectedDistrictToDataStore
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
    private val saveSelectedDistrictToDataStore: SaveSelectedDistrictToDataStore
) : ViewModel() {

    val districtId = savedStateHandle.get<String>("districtId")

    private val _state = MutableStateFlow(OnboardingCompleteState())
    val state: StateFlow<OnboardingCompleteState> = _state

    fun saveHadisListWithJson(json: String) {
        viewModelScope.launch {
            async { saveHadisList(json) }
            async {
                getDistrictDataByDistrictId(districtId?.toInt() ?: 0).collectLatest { response ->
                    if (response is BaseResourceEvent.Success) {
                        saveSelectedDistrictToDataStore(
                            response.data ?: DistrictData(
                                districtId = 0,
                                districtName = ""
                            )
                        )
                    }
                    _state.update {
                        it.copy(
                            districtData = response
                        )
                    }
                }
            }
        }
    }
}