package com.mesutemre.namazvakitleri.core.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION1TO1_2 = object : Migration(1, 1_2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `HadisEntity` (`id` INTEGER NOT NULL,`content` TEXT NOT NULL, PRIMARY KEY(`id`))")
    }
}