package com.mesutemre.namazvakitleri.dashboard.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TarihteBugunItemDto(

    @SerializedName("Gun")
    val gun: String,

    @SerializedName("Ay")
    val ay: String,

    @SerializedName("Yil")
    val yil: String,

    @SerializedName("Durum")
    val durum: String,

    @SerializedName("Olay")
    val olay: String
)
