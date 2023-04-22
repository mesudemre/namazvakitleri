package com.mesutemre.namazvakitleri.onboarding.data.mapper

import com.mesutemre.namazvakitleri.onboarding.data.local.entity.DistrictEntity
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.DistrictDto
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import javax.inject.Inject

class DistrictDataMapper @Inject constructor() {

    fun convertDistrictDtoToDistrctData(districtDto: DistrictDto): DistrictData {
        return DistrictData(
            districtId = districtDto.id,
            districtName = districtDto.ilceAd
        )
    }

    fun convertDistrictEntityToDistrictData(districtEntity: DistrictEntity): DistrictData {
        return DistrictData(
            districtId = districtEntity.ilceId ?: 0,
            districtName = districtEntity.ilceAd ?: ""
        )
    }

    fun convertDistrictDataToDistrictEntity(
        districtData: DistrictData,
        cityId: Int
    ): DistrictEntity {
        return DistrictEntity(
            ilceId = districtData.districtId,
            ilceAd = districtData.districtName,
            ilId = cityId
        )
    }
}