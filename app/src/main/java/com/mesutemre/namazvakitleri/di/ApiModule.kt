package com.mesutemre.namazvakitleri.di

import com.mesutemre.namazvakitleri.dashboard.data.remote.IDashboardApi
import com.mesutemre.namazvakitleri.dashboard.data.remote.ITarihteBugunApi
import com.mesutemre.namazvakitleri.onboarding.data.remote.IOnboardingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideOnboardingApi(retrofit: Retrofit): IOnboardingApi =
        retrofit.create(IOnboardingApi::class.java)

    @Singleton
    @Provides
    fun provideDashboardApi(retrofit: Retrofit): IDashboardApi =
        retrofit.create(IDashboardApi::class.java)

    @Singleton
    @Provides
    fun provideTarihteBugunApi(@TarihteBugun retrofit: Retrofit): ITarihteBugunApi =
        retrofit.create(ITarihteBugunApi::class.java)

}