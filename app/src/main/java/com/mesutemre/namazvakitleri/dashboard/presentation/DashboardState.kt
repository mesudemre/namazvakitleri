package com.mesutemre.namazvakitleri.dashboard.presentation

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.domain.model.HadisData

data class DashboardState(
    val gunlukHadis: BaseResourceEvent<HadisData> = BaseResourceEvent.Loading(),
    val vakitInfo: BaseResourceEvent<VakitInfoData> = BaseResourceEvent.Loading(),
    val selectedDistrict: DistrictData? = null
)
