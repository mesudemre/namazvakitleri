package com.mesutemre.namazvakitleri.onboarding.data.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.data.local.asset.AyetAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.asset.HadisAssetData
import com.mesutemre.namazvakitleri.onboarding.domain.model.AyetData
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.domain.model.HadisData
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
    suspend fun getHadisAssetDataList(jsonString: String): List<HadisAssetData>
    suspend fun saveHadisList(list: List<HadisAssetData>)
    suspend fun getAndSaveHadisList(jsonString: String)
    suspend fun getHadisByDayOfMonth(): Flow<BaseResourceEvent<HadisData>>
    suspend fun getDistrictByDistrictId(districtId: Int): Flow<BaseResourceEvent<DistrictData>>
    suspend fun saveSelectedDistrictToDataStore(districtData: DistrictData)
    suspend fun saveAyetList(list: List<AyetAssetData>)
    suspend fun getAndSaveAyetList(jsonString: String)
    suspend fun getAyetByDayOfMonth(): Flow<BaseResourceEvent<AyetData>>
    suspend fun getAndSaveNotificationToken(ilceId: String)
    suspend fun isTokenExistInFirebase(token: String,onComplete:(Boolean) -> Unit)
    suspend fun getSavedPushTokenFromDataStore(): String
    fun saveTokenIlceToFirebase(token: String, ilceId: String)
}