package com.mesutemre.namazvakitleri.onboarding.data.local

import com.mesutemre.namazvakitleri.onboarding.data.local.asset.HadisAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.DistrictEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.HadisEntity
import kotlinx.coroutines.flow.Flow

interface IOnboardingLocalDataSource {

    suspend fun getCityList(): List<CityEntity>
    suspend fun saveCity(vararg cityEntity: CityEntity)
    suspend fun isCitiesSaved(): Flow<Boolean>
    suspend fun saveCityForControl()
    suspend fun getDistrictListByCityId(cityId: Int): Map<CityEntity, List<DistrictEntity>>
    suspend fun saveDistrict(vararg districtEntity: DistrictEntity)
    suspend fun isDistrictListSavedBefore(cityId: Int): Boolean
    suspend fun getHadisAssetDataList(jsonString: String): List<HadisAssetData>
    suspend fun saveHadisList(vararg hadisEntity: HadisEntity)
    suspend fun getHadisById(id: Int): HadisEntity
}