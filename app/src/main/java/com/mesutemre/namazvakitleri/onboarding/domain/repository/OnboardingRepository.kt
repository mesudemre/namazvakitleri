package com.mesutemre.namazvakitleri.onboarding.domain.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.core.repository.BaseRepository
import com.mesutemre.namazvakitleri.di.IoDispatcher
import com.mesutemre.namazvakitleri.onboarding.data.local.OnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.data.local.asset.HadisAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.HadisEntity
import com.mesutemre.namazvakitleri.onboarding.data.mapper.CityDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.DistrictDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.HadisAssetDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.remote.OnboardingRemoteDataSource
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.domain.model.HadisData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class OnboardingRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val onboardingLocalDataSource: OnboardingLocalDataSource,
    private val onboardingRemoteDataSource: OnboardingRemoteDataSource,
    private val cityDataMapper: CityDataMapper,
    private val districtDataMapper: DistrictDataMapper,
    private val hadisAssetDataMapper: HadisAssetDataMapper
) : BaseRepository(ioDispatcher), IOnboardingRepository {

    override suspend fun getCityListFromAPI(): Flow<BaseResourceEvent<List<CityData>>> {
        return callApi(call = {
            onboardingRemoteDataSource.getCityList()
        }, mapperCall = {
            it.map { data ->
                cityDataMapper.convertCityDtoToCityData(data)
            }
        })
    }

    override suspend fun getCityListFromDB(): Flow<BaseResourceEvent<List<CityData>>> {
        return callDb(call = {
            onboardingLocalDataSource.getCityList()
        }, mapperCall = {
            it.map { data ->
                cityDataMapper.convertCityEntityToCityData(data)
            }
        })
    }

    override suspend fun saveCity(list: List<CityData>) {
        onboardingLocalDataSource.saveCity(*(list.map {
            cityDataMapper.convertCityDataToCityEntity(it)
        }).toTypedArray())
    }

    override suspend fun isCitiesSaved(): Flow<Boolean> = onboardingLocalDataSource.isCitiesSaved()

    override suspend fun saveCityControl() {
        onboardingLocalDataSource.saveCityForControl()
    }

    override suspend fun getDistrictListFromAPI(cityId: Int): Flow<BaseResourceEvent<List<DistrictData>>> {
        return callApi(call = {
            onboardingRemoteDataSource.getDistrictList(cityId)
        }, mapperCall = {
            it.map { data ->
                districtDataMapper.convertDistrictDtoToDistrctData(data)
            }
        })
    }

    override suspend fun getDistrictListFromDBByCityId(cityId: Int): Flow<BaseResourceEvent<List<DistrictData>>> {
        return callDb(call = {
            onboardingLocalDataSource.getDistrictListByCityId(cityId)
        }, mapperCall = {
            it.values.toList()[0].map { data ->
                districtDataMapper.convertDistrictEntityToDistrictData(data)
            }
        })
    }

    override suspend fun saveDistrict(list: List<DistrictData>, cityId: Int) {
        onboardingLocalDataSource.saveDistrict(*(list.map {
            districtDataMapper.convertDistrictDataToDistrictEntity(it, cityId)
        }).toTypedArray())
    }

    override suspend fun isDistrictListSavedBefore(cityId: Int): Boolean =
        onboardingLocalDataSource.isDistrictListSavedBefore(cityId)

    override suspend fun getHadisAssetDataList(jsonString: String): List<HadisAssetData> =
        onboardingLocalDataSource.getHadisAssetDataList(jsonString)

    override suspend fun saveHadisList(list: List<HadisAssetData>) {
        onboardingLocalDataSource.saveHadisList(
            *(
                    list.map {
                        hadisAssetDataMapper.convertHadisAssetDataToHadisEntity(it)
                    }).toTypedArray()
        )
    }

    override suspend fun getAndSaveHadisList(jsonString: String) {
        val list = getHadisAssetDataList(jsonString)
        saveHadisList(list)
    }

    override suspend fun getHadisByDayOfMonth(): Flow<BaseResourceEvent<HadisData>>  {
        val calendar = Calendar.getInstance()
        return callDb(
           call =  {
                onboardingLocalDataSource.getHadisById(calendar.get(Calendar.DAY_OF_MONTH))
            },
            mapperCall = {
                hadisAssetDataMapper.convertHadisEntityToHadisData(it)
            }
        )
    }
}