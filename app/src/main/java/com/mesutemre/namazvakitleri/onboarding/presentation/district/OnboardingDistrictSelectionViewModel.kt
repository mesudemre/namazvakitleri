package com.mesutemre.namazvakitleri.onboarding.presentation.district

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetDistrictListByCityId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingDistrictSelectionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getDistrictListByCityId: GetDistrictListByCityId
) : ViewModel() {

    val cityId = savedStateHandle.get<String>("cityId")

    private val _state = MutableStateFlow(OnboardingDistrictSelectionState())
    val state: StateFlow<OnboardingDistrictSelectionState> = _state

    init {
        viewModelScope.launch {
            getDistrictListByCityId(cityId = cityId?.toInt() ?: 0).collectLatest { response ->
                _state.update {
                    it.copy(
                        districtList = response
                    )
                }
            }
        }
    }
}