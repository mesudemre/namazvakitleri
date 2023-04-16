package com.mesutemre.namazvakitleri.onboarding.presentation.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetCityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingCitySelectionViewModel @Inject constructor(
    private val getCityList: GetCityList
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingCitySelecionState())
    val state: StateFlow<OnboardingCitySelecionState> = _state

    init {
        viewModelScope.launch {
            getCityList().collectLatest { response ->
                _state.update {
                    it.copy(
                        cityList = response
                    )
                }
            }
        }
    }
}