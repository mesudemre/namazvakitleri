package com.mesutemre.namazvakitleri.di

import com.mesutemre.namazvakitleri.core.database.NamazvakitleriDatabase
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
object DaoModule {

    @Singleton
    @Provides
    fun provideOnboardingDao(database: NamazvakitleriDatabase) = database.getOnBoardingDao()
}