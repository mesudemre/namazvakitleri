package com.mesutemre.namazvakitleri.onboarding.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DistrictDto(
    @SerializedName("IlceID")
    val id: Int,

    @SerializedName("IlceAdi")
    val ilceAd: String,

    @SerializedName("IlceAdiEn")
    val ilceAdEng: String
)
