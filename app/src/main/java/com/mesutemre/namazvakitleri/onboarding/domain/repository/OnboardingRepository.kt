package com.mesutemre.namazvakitleri.onboarding.domain.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.core.repository.BaseRepository
import com.mesutemre.namazvakitleri.di.IoDispatcher
import com.mesutemre.namazvakitleri.onboarding.data.local.OnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.data.mapper.CityDtoToCityDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.CityEntityToCityDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.remote.OnboardingRemoteDataSource
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val onboardingLocalDataSource: OnboardingLocalDataSource,
    private val onboardingRemoteDataSource: OnboardingRemoteDataSource,
    private val cityDtoToCityDataMapper: CityDtoToCityDataMapper,
    private val entityToCityDataMapper: CityEntityToCityDataMapper
) : BaseRepository(ioDispatcher), IOnboardingRepository {

    override suspend fun getCityListFromAPI(): Flow<BaseResourceEvent<List<CityData>>> {
        return callApi(call = {
            onboardingRemoteDataSource.getCityList()
        }, mapperCall = {
            it.map { data ->
                cityDtoToCityDataMapper(data)
            }
        })
    }

    override suspend fun getCityListFromDB(): Flow<BaseResourceEvent<List<CityData>>> {
        return callDb(call = {
            onboardingLocalDataSource.getCityList()
        }, mapperCall = {
            it.map { data ->
                entityToCityDataMapper(data)
            }
        })
    }

    override suspend fun saveCity(cityEntity: CityEntity) {
        callDb(call = {
            onboardingLocalDataSource.saveCity(cityEntity)
        }, mapperCall = {})
    }

    override suspend fun isCitiesSaved(): Flow<Boolean> = onboardingLocalDataSource.isCitiesSaved()


}