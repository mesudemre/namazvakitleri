package com.mesutemre.namazvakitleri.onboarding.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(

    @PrimaryKey
    @ColumnInfo(name = "sehirId")
    val sehirId: Int? = 0,

    @ColumnInfo(name = "sehirAd")
    val sehirAd:String? = null
)
