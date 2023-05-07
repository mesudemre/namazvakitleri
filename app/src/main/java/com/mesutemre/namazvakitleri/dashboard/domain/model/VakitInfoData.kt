package com.mesutemre.namazvakitleri.dashboard.domain.model

data class VakitInfoData(
    val imsak: VakitInfoTypeData,
    val gunes: VakitInfoTypeData,
    val ogle: VakitInfoTypeData,
    val ikindi: VakitInfoTypeData,
    val aksam: VakitInfoTypeData,
    val yatsi: VakitInfoTypeData,
    val hicriTakvimInfo: String,
    val miladiTakvimInfo: String,
    val miladiTarih: String
)
