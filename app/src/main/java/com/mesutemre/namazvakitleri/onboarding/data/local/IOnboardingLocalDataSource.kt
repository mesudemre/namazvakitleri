package com.mesutemre.namazvakitleri.onboarding.data.local

import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

interface IOnboardingLocalDataSource {

    suspend fun getCityList(): List<CityEntity>
    suspend fun saveCity(cityEntity: CityEntity)
    suspend fun isCitiesSaved(): Flow<Boolean>
}