package com.mesutemre.namazvakitleri.onboarding.data.mapper

import com.mesutemre.namazvakitleri.onboarding.data.local.asset.AyetAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.AyetEntity
import com.mesutemre.namazvakitleri.onboarding.domain.model.AyetData
import javax.inject.Inject

class AyetDataMapper @Inject constructor() {

    fun convertAyetAssetDataToAyetEntity(ayetAssetData: AyetAssetData): AyetEntity {
        return AyetEntity(
            ayetId = ayetAssetData.id,
            ayetContent = ayetAssetData.content
        )
    }

    fun convertAyetEntityToAyetData(ayetEntity: AyetEntity): AyetData {
        return AyetData(content = ayetEntity.ayetContent)
    }
}