package com.mesutemre.namazvakitleri.onboarding.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.core.ext.readBoolean
import com.mesutemre.namazvakitleri.core.ext.saveData
import com.mesutemre.namazvakitleri.onboarding.data.local.asset.HadisAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.DistrictEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.HadisEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingLocalDataSource @Inject constructor(
    private val dao: IOnboardingDao,
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) : IOnboardingLocalDataSource {

    override suspend fun getCityList(): List<CityEntity> = dao.getCityList()

    override suspend fun saveCity(vararg cityEntity: CityEntity) {
        dao.saveCity(*cityEntity)
    }

    override suspend fun isCitiesSaved(): Flow<Boolean> =
        dataStore.readBoolean(Constants.DataStoreConstants.CITY_LIST_KEY, false)

    override suspend fun saveCityForControl() {
        dataStore.saveData(Constants.DataStoreConstants.CITY_LIST_KEY, true)
    }

    override suspend fun getDistrictListByCityId(cityId: Int): Map<CityEntity, List<DistrictEntity>> =
        dao.getDistrictListByCityId(cityId)

    override suspend fun saveDistrict(vararg districtEntity: DistrictEntity) {
        dao.saveDistrict(*districtEntity)
    }

    override suspend fun isDistrictListSavedBefore(cityId: Int): Boolean =
        dao.isDistrictListSavedBefore(cityId)

    override suspend fun getHadisAssetDataList(jsonString: String): List<HadisAssetData> {
        val listHadisType = object : TypeToken<List<HadisAssetData>>() {}.type
        return gson.fromJson(jsonString, listHadisType)
    }

    override suspend fun saveHadisList(vararg hadisEntity: HadisEntity) {
        dao.saveHadis(*hadisEntity)
    }

    override suspend fun getHadisById(id: Int): HadisEntity = dao.getHadisById(id)
}