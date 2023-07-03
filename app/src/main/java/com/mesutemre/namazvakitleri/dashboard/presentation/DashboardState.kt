package com.mesutemre.namazvakitleri.dashboard.presentation

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.domain.model.DashboardVakitPageType
import com.mesutemre.namazvakitleri.dashboard.domain.model.TarihteBugunData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoScreenData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitType
import com.mesutemre.namazvakitleri.onboarding.domain.model.AyetData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.domain.model.HadisData

data class DashboardState(
    val gunlukHadis: BaseResourceEvent<HadisData> = BaseResourceEvent.Loading(),
    val vakitInfo: BaseResourceEvent<VakitInfoScreenData> = BaseResourceEvent.Loading(),
    val selectedDistrict: DistrictData? = null,
    val gunlukAyet: BaseResourceEvent<AyetData> = BaseResourceEvent.Loading(),
    val tarihteBugunList: BaseResourceEvent<List<TarihteBugunData>> = BaseResourceEvent.Loading(),
    val activeVakitPage: DashboardVakitPageType = DashboardVakitPageType.DEFAULT,
    val geriSayim: Int = 0,
    val kalanSaat: Int = 0,
    val kalanDakika: Int = 0,
    val kalanSaniye: Int = 0,
    val currentVakit: VakitType? = null,
    val ayetListJsonAl: Boolean = false,
    val hadisListJsonAl: Boolean = false,
    val showCumaSnack: Boolean = false
)
