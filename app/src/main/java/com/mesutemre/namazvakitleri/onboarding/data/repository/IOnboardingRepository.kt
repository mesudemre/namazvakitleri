package com.mesutemre.namazvakitleri.onboarding.data.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import kotlinx.coroutines.flow.Flow

interface IOnboardingRepository {

    suspend fun getCityListFromAPI(): Flow<BaseResourceEvent<List<CityData>>>
    suspend fun getCityListFromDB(): Flow<BaseResourceEvent<List<CityData>>>
    suspend fun saveCity(list: List<CityData>)
    suspend fun isCitiesSaved(): Flow<Boolean>
    suspend fun saveCityControl()
    suspend fun getDistrictListFromAPI(cityId: Int): Flow<BaseResourceEvent<List<DistrictData>>>
    suspend fun getDistrictListFromDBByCityId(cityId: Int): Flow<BaseResourceEvent<List<DistrictData>>>
    suspend fun saveDistrict(list: List<DistrictData>, cityId: Int)
    suspend fun isDistrictListSavedBefore(cityId: Int): Boolean
}