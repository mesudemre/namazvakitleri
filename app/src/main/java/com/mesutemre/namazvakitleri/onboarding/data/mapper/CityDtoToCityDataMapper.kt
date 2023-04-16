package com.mesutemre.namazvakitleri.onboarding.data.mapper

import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import javax.inject.Inject

class CityDtoToCityDataMapper @Inject constructor() {

    operator fun invoke(cityDto: CityDto): CityData {
        return CityData(
            cityId = cityDto.id,
            cityName = cityDto.sehirAd
        )
    }
}