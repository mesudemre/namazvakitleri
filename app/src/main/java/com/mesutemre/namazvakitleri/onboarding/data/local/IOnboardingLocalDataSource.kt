package com.mesutemre.namazvakitleri.onboarding.data.local

import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.DistrictEntity
import kotlinx.coroutines.flow.Flow

interface IOnboardingLocalDataSource {

    suspend fun getCityList(): List<CityEntity>
    suspend fun saveCity(vararg cityEntity: CityEntity)
    suspend fun isCitiesSaved(): Flow<Boolean>
    suspend fun saveCityForControl()
    suspend fun getDistrictListByCityId(cityId: Int): Map<CityEntity,List<DistrictEntity>>
    suspend fun saveDistrict(vararg districtEntity: DistrictEntity)
    suspend fun isDistrictListSavedBefore(cityId: Int): Boolean
}