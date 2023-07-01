package com.mesutemre.namazvakitleri.onboarding.data.mapper

import com.mesutemre.namazvakitleri.onboarding.data.local.asset.HadisAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.HadisEntity
import com.mesutemre.namazvakitleri.onboarding.domain.model.HadisData
import javax.inject.Inject

class HadisAssetDataMapper @Inject constructor() {

    fun convertHadisAssetDataToHadisEntity(hadisAssetData: HadisAssetData): HadisEntity {
        return HadisEntity(
            hadisId = hadisAssetData.id,
            hadisContent = hadisAssetData.content,
            hadisSource = hadisAssetData.source
        )
    }

    fun convertHadisEntityToHadisData(entity: HadisEntity): HadisData {
        return HadisData(
            content = entity.hadisContent,
            kaynak = entity.hadisSource ?: ""
        )
    }
}