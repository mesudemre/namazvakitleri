package com.mesutemre.namazvakitleri.onboarding.presentation.complete

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.SaveHadisList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingCompleteViewModel @Inject constructor(
    private val saveHadisList: SaveHadisList,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val districtId = savedStateHandle.get<String>("districtId")

    fun saveHadisListWithJson(json: String) {
        viewModelScope.launch {
            saveHadisList(json)
        }
    }
}