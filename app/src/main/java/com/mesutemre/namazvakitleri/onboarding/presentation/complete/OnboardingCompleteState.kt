package com.mesutemre.namazvakitleri.onboarding.presentation.complete

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData

data class OnboardingCompleteState(
    val districtData: BaseResourceEvent<DistrictData> = BaseResourceEvent.Nothing()
)
