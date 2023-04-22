package com.mesutemre.namazvakitleri.di

import com.mesutemre.namazvakitleri.onboarding.data.local.OnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.data.mapper.CityDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.DistrictDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.remote.OnboardingRemoteDataSource
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.repository.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideOnboardingRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        localDataSource: OnboardingLocalDataSource,
        remoteDataSource: OnboardingRemoteDataSource,
        cityDataMapper: CityDataMapper,
        districtDataMapper: DistrictDataMapper
    ): IOnboardingRepository {
        return OnboardingRepository(ioDispatcher, localDataSource, remoteDataSource,cityDataMapper,districtDataMapper)
    }
}