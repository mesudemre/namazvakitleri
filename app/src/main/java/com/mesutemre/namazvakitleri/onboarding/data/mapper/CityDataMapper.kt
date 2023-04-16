package com.mesutemre.namazvakitleri.onboarding.data.mapper

import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import javax.inject.Inject

class CityDataMapper @Inject constructor() {

    fun convertCityDtoToCityData(cityDto: CityDto): CityData {
        return CityData(
            cityId = cityDto.id,
            cityName = cityDto.sehirAd
        )
    }

    fun convertCityEntityToCityData(cityEntity: CityEntity): CityData {
        return CityData(
            cityId = cityEntity.sehirId ?: 0,
            cityName = cityEntity.sehirAd ?: ""
        )
    }

    fun convertCityDataToCityEntity(cityDto: CityData): CityEntity {
        return CityEntity(
            sehirId = cityDto.cityId,
            sehirAd = cityDto.cityName
        )
    }
}