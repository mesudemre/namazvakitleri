package com.mesutemre.namazvakitleri.dashboard.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VakitInfoDto(

    @SerializedName("Aksam")
    val aksam: String,

    @SerializedName("Gunes")
    val gunes: String,

    @SerializedName("GunesDogus")
    val gunesDogus: String,

    @SerializedName("GunesBatis")
    val gunesBatis: String,

    @SerializedName("HicriTarihKisa")
    val hicriTarihKisa: String,

    @SerializedName("HicriTarihUzun")
    val hicriTarihUzun: String,

    @SerializedName("Ikindi")
    val ikindi: String,

    @SerializedName("Imsak")
    val imsak: String,

    @SerializedName("KibleSaati")
    val kibleSaati: String,

    @SerializedName("MiladiTarihKisa")
    val miladiTarihKisa: String,

    @SerializedName("MiladiTarihUzun")
    val miladiTarihUzun: String,

    @SerializedName("Ogle")
    val ogle: String,

    @SerializedName("Yatsi")
    val yatsi: String
)
