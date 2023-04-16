package com.mesutemre.namazvakitleri.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingDao
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity

@Database(
    version = 1,
    entities = [CityEntity::class]
)
abstract class NamazvakitleriDatabase : RoomDatabase() {

    abstract fun getOnBoardingDao(): IOnboardingDao
}