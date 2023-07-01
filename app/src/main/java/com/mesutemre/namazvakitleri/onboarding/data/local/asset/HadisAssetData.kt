package com.mesutemre.namazvakitleri.onboarding.data.local.asset

import com.google.gson.annotations.SerializedName


data class HadisAssetData(

    @SerializedName("id")
    val id: Int,

    @SerializedName("content")
    val content: String,

    @SerializedName("source")
    val source: String
)
