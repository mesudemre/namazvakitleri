package com.mesutemre.namazvakitleri.onboarding.data.mapper

import com.mesutemre.namazvakitleri.onboarding.data.local.asset.AyetAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.AyetEntity
import com.mesutemre.namazvakitleri.onboarding.domain.model.AyetData
import javax.inject.Inject

class AyetDataMapper @Inject constructor() {

    fun convertAyetAssetDataToAyetEntity(ayetAssetData: AyetAssetData): AyetEntity {
        return AyetEntity(
            ayetId = ayetAssetData.id,
            ayetContent = ayetAssetData.content,
            sureAd = ayetAssetData.sureAdi,
            ayetNo = ayetAssetData.ayetNo
        )
    }

    fun convertAyetEntityToAyetData(ayetEntity: AyetEntity): AyetData {
        return AyetData(
            content = ayetEntity.ayetContent,
            sureAd = ayetEntity.sureAd ?: "",
            ayetNo = ayetEntity.ayetNo ?: 0
        )
    }
}