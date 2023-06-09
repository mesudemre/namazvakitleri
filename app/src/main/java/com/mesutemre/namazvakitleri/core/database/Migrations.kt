package com.mesutemre.namazvakitleri.core.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION1TO1_2 = object : Migration(1, 1_2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `HadisEntity` (`id` INTEGER NOT NULL,`content` TEXT NOT NULL, PRIMARY KEY(`id`))")
    }
}

val MIGRATION1_2TO1_3 = object : Migration(1_2, 1_3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `VakitInfoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `imsak` TEXT NOT NULL, `gunes` TEXT NOT NULL, `ogle` TEXT NOT NULL, `ikindi` TEXT NOT NULL, `aksam` TEXT NOT NULL, `yatsi` TEXT NOT NULL, `miladiTarih` TEXT NOT NULL, `hicriTarihUzun` TEXT NOT NULL, `miladiTarihUzun` TEXT NOT NULL)")
    }
}

val MIGRATION1_3TO1_4 = object : Migration(1_3, 1_4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `TarihteBugunEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `durum` TEXT NOT NULL, `olay` TEXT NOT NULL)")
    }
}

val MIGRATION1_4TO1_5 = object : Migration(1_4, 1_5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE `TarihteBugunEntity`")
        database.execSQL("CREATE TABLE IF NOT EXISTS `TarihteBugunEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `durum` TEXT NOT NULL, `olay` TEXT NOT NULL,`tarih` TEXT NOT NULL)")
    }
}