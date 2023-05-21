package com.mesutemre.namazvakitleri.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mesutemre.namazvakitleri.dashboard.data.local.IDashboardDao
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.TarihteBugunEntity
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingDao
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.AyetEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.DistrictEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.HadisEntity

@Database(
    version = 1_5,
    entities = [CityEntity::class, DistrictEntity::class, HadisEntity::class, VakitInfoEntity::class, AyetEntity::class, TarihteBugunEntity::class],
)
abstract class NamazvakitleriDatabase : RoomDatabase() {

    abstract fun getOnBoardingDao(): IOnboardingDao
    abstract fun getDashboardDao(): IDashboardDao
}