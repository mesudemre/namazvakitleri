package com.mesutemre.namazvakitleri.di

import android.content.Context
import androidx.room.Room
import com.mesutemre.namazvakitleri.core.database.MIGRATION1TO1_2
import com.mesutemre.namazvakitleri.core.database.MIGRATION1_2TO1_3
import com.mesutemre.namazvakitleri.core.database.NamazvakitleriDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: mesutemre.celenk
 * @Date: 15.04.2023
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NamazvakitleriDatabase::class.java,
        "namazvakitleri_db"
    ).addMigrations(MIGRATION1TO1_2)
        .addMigrations(MIGRATION1_2TO1_3)
        .build()
}