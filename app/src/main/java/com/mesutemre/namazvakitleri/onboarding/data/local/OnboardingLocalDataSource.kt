package com.mesutemre.namazvakitleri.onboarding.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mesutemre.namazvakitleri.core.ext.readBoolean
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingLocalDataSource @Inject constructor(
    private val dao: IOnboardingDao,
    private val dataStore: DataStore<Preferences>
) : IOnboardingLocalDataSource {

    override suspend fun getCityList(): List<CityEntity> = dao.getCityList()

    override suspend fun saveCity(cityEntity: CityEntity) {
        dao.saveCity(cityEntity)
    }

    override suspend fun isCitiesSaved(): Flow<Boolean> = dataStore.readBoolean("", false)
}