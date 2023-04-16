package com.mesutemre.namazvakitleri.onboarding.data.mapper

import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import javax.inject.Inject

class CityEntityToCityDataMapper @Inject constructor() {

    operator fun invoke(cityEntity: CityEntity): CityData {
        return CityData(
            cityId = cityEntity.sehirId ?: 0,
            cityName = cityEntity.sehirAd ?: ""
        )
    }
}