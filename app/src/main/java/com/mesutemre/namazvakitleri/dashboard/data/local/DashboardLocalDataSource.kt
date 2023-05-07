package com.mesutemre.namazvakitleri.dashboard.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.core.ext.readBoolean
import com.mesutemre.namazvakitleri.core.ext.saveData
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardLocalDataSource @Inject constructor(
    private val dao: IDashboardDao,
    private val dataStore: DataStore<Preferences>
) : IDashboardLocalDataSource {

    override suspend fun getVakitInfo(tarih: String): VakitInfoEntity = dao.getVakitInfo(tarih)

    override suspend fun saveVakitInfo(vararg vakitInfoEntity: VakitInfoEntity) {
        dao.saveVakitInfo(*vakitInfoEntity)
    }

    override suspend fun checkVakitListSaved(): Flow<Boolean> =
        dataStore.readBoolean(Constants.DataStoreConstants.VAKIT_EXIST_KEY, false)

    override suspend fun saveCheckVakitListSaved(isExist: Boolean) {
        dataStore.saveData(Constants.DataStoreConstants.VAKIT_EXIST_KEY, isExist)
    }
}
