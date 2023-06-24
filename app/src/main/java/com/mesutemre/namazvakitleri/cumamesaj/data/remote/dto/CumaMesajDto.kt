package com.mesutemre.namazvakitleri.cumamesaj.data.remote.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class CumaMesajDto(
    val mesaj: String = "",
    val resim: String = "",
    val resimAciklama: String = ""
)
