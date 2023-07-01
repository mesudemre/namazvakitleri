package com.mesutemre.namazvakitleri.onboarding.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HadisEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val hadisId: Int,

    @ColumnInfo(name = "content")
    val hadisContent: String,

    @ColumnInfo(name = "source")
    val hadisSource: String? = null
)
