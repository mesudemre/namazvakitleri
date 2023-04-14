package com.mesutemre.namazvakitleri.onboarding.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CityDto(

    @SerializedName("SehirID")
    val id: Int,

    @SerializedName("SehirAdi")
    val sehirAd: String,

    @SerializedName("SehirAdiEn")
    val sehirAdEng: String
)
