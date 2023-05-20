package com.mesutemre.namazvakitleri.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.mesutemre.namazvakitleri.dashboard.data.local.DashboardLocalDataSource
import com.mesutemre.namazvakitleri.dashboard.data.local.IDashboardDao
import com.mesutemre.namazvakitleri.dashboard.data.local.IDashboardLocalDataSource
import com.mesutemre.namazvakitleri.dashboard.data.remote.DashboardRemoteDataSource
import com.mesutemre.namazvakitleri.dashboard.data.remote.IDashboardApi
import com.mesutemre.namazvakitleri.dashboard.data.remote.IDashboardRemoteDataSource
import com.mesutemre.namazvakitleri.dashboard.data.remote.ITarihteBugunApi
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingDao
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.data.local.OnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.data.remote.IOnboardingApi
import com.mesutemre.namazvakitleri.onboarding.data.remote.IOnboardingRemoteDataSource
import com.mesutemre.namazvakitleri.onboarding.data.remote.OnboardingRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: mesutemre.celenk
 * @Date: 16.04.2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideOnboardingLocalDataSource(
        dao: IOnboardingDao,
        dataStore: DataStore<Preferences>,
        gson: Gson
    ): IOnboardingLocalDataSource {
        return OnboardingLocalDataSource(
            dao, dataStore, gson
        )
    }

    @Singleton
    @Provides
    fun provideOnboardingRemoteDataSource(
        api: IOnboardingApi
    ): IOnboardingRemoteDataSource {
        return OnboardingRemoteDataSource(
            api
        )
    }

    @Singleton
    @Provides
    fun provideDashboardRemoteDataSource(
        api: IDashboardApi,
        tarihteBugunApi: ITarihteBugunApi
    ): IDashboardRemoteDataSource {
        return DashboardRemoteDataSource(api, tarihteBugunApi)
    }

    @Singleton
    @Provides
    fun provideDashboardLocalDataSource(
        dao: IDashboardDao,
        dataStore: DataStore<Preferences>
    ): IDashboardLocalDataSource {
        return DashboardLocalDataSource(
            dao = dao,
            dataStore = dataStore
        )
    }
}