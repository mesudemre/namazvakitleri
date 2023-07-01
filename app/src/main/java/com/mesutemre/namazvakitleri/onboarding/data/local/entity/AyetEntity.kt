package com.mesutemre.namazvakitleri.onboarding.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AyetEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val ayetId: Int,

    @ColumnInfo(name = "content")
    val ayetContent: String,

    @ColumnInfo(name = "ayetNo")
    val ayetNo: Int? = null,

    @ColumnInfo(name = "sureAd")
    val sureAd: String? = null
)
