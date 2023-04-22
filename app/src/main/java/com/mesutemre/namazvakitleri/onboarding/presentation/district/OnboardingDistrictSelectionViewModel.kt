package com.mesutemre.namazvakitleri.onboarding.presentation.district

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetDistrictListByCityId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingDistrictSelectionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getDistrictListByCityId: GetDistrictListByCityId
) : ViewModel() {

    val cityId = savedStateHandle.get<String>("cityId")

    init {
        Log.d("CITY_ID","$cityId")
    }
}