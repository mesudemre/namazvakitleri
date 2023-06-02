package com.mesutemre.namazvakitleri.onboarding.domain.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.core.repository.BaseRepository
import com.mesutemre.namazvakitleri.di.IoDispatcher
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.data.local.asset.AyetAssetData
import com.mesutemre.namazvakitleri.onboarding.data.local.asset.HadisAssetData
import com.mesutemre.namazvakitleri.onboarding.data.mapper.AyetDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.CityDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.DistrictDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.HadisAssetDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.remote.IOnboardingRemoteDataSource
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.FirebaseNotificationToken
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.AyetData
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.domain.model.HadisData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class OnboardingRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val onboardingLocalDataSource: IOnboardingLocalDataSource,
    private val onboardingRemoteDataSource: IOnboardingRemoteDataSource,
    private val cityDataMapper: CityDataMapper,
    private val districtDataMapper: DistrictDataMapper,
    private val hadisAssetDataMapper: HadisAssetDataMapper,
    private val ayetDataMapper: AyetDataMapper
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

    override suspend fun getHadisByDayOfMonth(): Flow<BaseResourceEvent<HadisData>> {
        val calendar = Calendar.getInstance()
        return callDb(
            call = {
                onboardingLocalDataSource.getHadisById(calendar.get(Calendar.DAY_OF_MONTH))
            },
            mapperCall = {
                hadisAssetDataMapper.convertHadisEntityToHadisData(it)
            }
        )
    }

    override suspend fun getDistrictByDistrictId(districtId: Int): Flow<BaseResourceEvent<DistrictData>> {
        return callDb(
            call = {
                onboardingLocalDataSource.getDistrictByDistrictId(districtId)
            },
            mapperCall = {
                districtDataMapper.convertDistrictEntityToDistrictData(it)
            }
        )
    }

    override suspend fun saveSelectedDistrictToDataStore(districtData: DistrictData) {
        onboardingLocalDataSource.saveSelectedDistrictToDataStore(districtData)
    }

    override suspend fun saveAyetList(list: List<AyetAssetData>) {
        onboardingLocalDataSource.saveAyetList(
            *(
                    list.map {
                        ayetDataMapper.convertAyetAssetDataToAyetEntity(it)
                    }).toTypedArray()
        )
    }

    override suspend fun getAndSaveAyetList(jsonString: String) {
        val list = onboardingLocalDataSource.getAyetAssetDataList(jsonString)
        saveAyetList(list)
    }

    override suspend fun getAyetByDayOfMonth(): Flow<BaseResourceEvent<AyetData>> {
        val calendar = Calendar.getInstance()
        return callDb(
            call = {
                onboardingLocalDataSource.getAyetById(calendar.get(Calendar.DAY_OF_MONTH))
            },
            mapperCall = {
                ayetDataMapper.convertAyetEntityToAyetData(it)
            }
        )
    }

    override suspend fun getAndSaveNotificationToken(ilceId: String) {
        val notificationToken = onboardingRemoteDataSource.getNotificationTokenFromFirebase()
        if (notificationToken.isSuccessful) {
            val token = notificationToken.result
            withContext(ioDispatcher) {
                async {
                    onboardingLocalDataSource.savePushTokenToDataStore(token)
                }
                async {
                    onboardingRemoteDataSource.saveTokenIlceToFirebase(
                        FirebaseNotificationToken(
                            token = token,
                            ilceId = ilceId
                        )
                    )
                }
            }
        }
    }
}