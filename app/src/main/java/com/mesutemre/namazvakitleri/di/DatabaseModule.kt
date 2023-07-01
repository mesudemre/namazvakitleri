package com.mesutemre.namazvakitleri.di

import android.content.Context
import androidx.room.Room
import com.mesutemre.namazvakitleri.core.database.*
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
        .addMigrations(MIGRATION1_3TO1_4)
        .addMigrations(MIGRATION1_4TO1_5)
        .addMigrations(MIGRATION1_5TO1_6)
        .addMigrations(MIGRATION1_6TO1_7)
        .addMigrations(MIGRATION1_7TO1_8)
        .addMigrations(MIGRATION1_8TO1_9)
        .addMigrations(MIGRATION1_9TO2_0)
        .build()
}