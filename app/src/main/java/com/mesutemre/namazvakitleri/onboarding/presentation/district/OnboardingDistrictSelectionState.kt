package com.mesutemre.namazvakitleri.onboarding.presentation.district

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData

data class OnboardingDistrictSelectionState(
    val districtList: BaseResourceEvent<List<DistrictData>> = BaseResourceEvent.Loading()
)
