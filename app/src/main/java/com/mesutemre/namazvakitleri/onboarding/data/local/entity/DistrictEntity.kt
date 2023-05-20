package com.mesutemre.namazvakitleri.onboarding.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DistrictEntity(

    @PrimaryKey
    @ColumnInfo(name = "ilceId")
    val ilceId: Int? = 0,

    @ColumnInfo(name = "ilceAd")
    val ilceAd: String? = null,

    @ColumnInfo(name = "ilId")
    val ilId: Int? = null
)
