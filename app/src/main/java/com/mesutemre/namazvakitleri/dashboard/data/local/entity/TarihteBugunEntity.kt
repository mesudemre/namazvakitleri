package com.mesutemre.namazvakitleri.dashboard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TarihteBugunEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val itemId: Int? = null,

    @ColumnInfo(name = "durum")
    val durum: String,

    @ColumnInfo(name = "olay")
    val olay: String
)
