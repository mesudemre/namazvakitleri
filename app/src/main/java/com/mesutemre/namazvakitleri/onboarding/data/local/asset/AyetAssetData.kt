package com.mesutemre.namazvakitleri.onboarding.data.local.asset

import com.google.gson.annotations.SerializedName

data class AyetAssetData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("content")
    val content: String,

    @SerializedName("sureAdi")
    val sureAdi: String,

    @SerializedName("ayetNumarasi")
    val ayetNo: Int
)
