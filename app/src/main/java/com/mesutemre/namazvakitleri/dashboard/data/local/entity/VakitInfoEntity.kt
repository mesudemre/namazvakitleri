package com.mesutemre.namazvakitleri.dashboard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VakitInfoEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val vakitId: Int? = null,

    @ColumnInfo(name = "imsak")
    val imsak: String,

    @ColumnInfo(name = "gunes")
    val gunes: String,

    @ColumnInfo(name = "ogle")
    val ogle: String,

    @ColumnInfo(name = "ikindi")
    val ikindi: String,

    @ColumnInfo(name = "aksam")
    val aksam: String,

    @ColumnInfo(name = "yatsi")
    val yatsi: String,

    @ColumnInfo(name = "miladiTarih")
    val miladiTarih: String,

    @ColumnInfo(name = "hicriTarihUzun")
    val hicriTarihUzun: String,

    @ColumnInfo(name = "miladiTarihUzun")
    val miladiTarihUzun: String

)
