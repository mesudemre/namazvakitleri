package com.mesutemre.namazvakitleri.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingDao
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity

@Database(
    version = 1,
    entities = arrayOf(
        CityEntity::class
    ),
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun getOnBoardingDao(): IOnboardingDao
}