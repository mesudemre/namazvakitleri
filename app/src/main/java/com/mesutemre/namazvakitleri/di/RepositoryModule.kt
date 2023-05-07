package com.mesutemre.namazvakitleri.di

import com.mesutemre.namazvakitleri.dashboard.data.local.IDashboardLocalDataSource
import com.mesutemre.namazvakitleri.dashboard.data.mapper.VakitDataMapper
import com.mesutemre.namazvakitleri.dashboard.data.remote.IDashboardRemoteDataSource
import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.repository.DashboardRepository
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.data.mapper.CityDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.DistrictDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.mapper.HadisAssetDataMapper
import com.mesutemre.namazvakitleri.onboarding.data.remote.IOnboardingRemoteDataSource
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
        localDataSource: IOnboardingLocalDataSource,
        remoteDataSource: IOnboardingRemoteDataSource,
        cityDataMapper: CityDataMapper,
        districtDataMapper: DistrictDataMapper,
        hadisAssetDataMapper: HadisAssetDataMapper
    ): IOnboardingRepository {
        return OnboardingRepository(
            ioDispatcher,
            localDataSource,
            remoteDataSource,
            cityDataMapper,
            districtDataMapper,
            hadisAssetDataMapper
        )
    }

    @Singleton
    @Provides
    fun provideDashboardRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        dashboardLocalDataSource: IDashboardLocalDataSource,
        dashboardRemoteDataSource: IDashboardRemoteDataSource,
        onboardingLocalDataSource: IOnboardingLocalDataSource,
        vakitDataMapper: VakitDataMapper
    ): IDashboardRepository {
        return DashboardRepository(
            ioDispatcher = ioDispatcher,
            dashboardLocalDataSource = dashboardLocalDataSource,
            dashboardRemoteDataSource = dashboardRemoteDataSource,
            onboardingLocalDataSource = onboardingLocalDataSource,
            vakitDataMapper = vakitDataMapper
        )
    }
}