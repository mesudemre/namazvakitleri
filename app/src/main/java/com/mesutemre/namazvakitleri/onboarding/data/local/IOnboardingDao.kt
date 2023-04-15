package com.mesutemre.namazvakitleri.onboarding.data.local

import androidx.room.Dao
import androidx.room.Query
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity

@Dao
interface IOnboardingDao {

    @Query("SELECT * FROM CityEntity")
    suspend fun getCityList(): List<CityEntity>
}