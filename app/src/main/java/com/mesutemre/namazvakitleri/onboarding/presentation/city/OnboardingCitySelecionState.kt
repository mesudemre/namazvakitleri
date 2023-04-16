package com.mesutemre.namazvakitleri.onboarding.presentation.city

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData

data class OnboardingCitySelecionState(
    val cityList: BaseResourceEvent<List<CityData>> = BaseResourceEvent.Loading()
)
