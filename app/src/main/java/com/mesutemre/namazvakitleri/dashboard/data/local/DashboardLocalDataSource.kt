package com.mesutemre.namazvakitleri.dashboard.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.core.ext.readBoolean
import com.mesutemre.namazvakitleri.core.ext.readInt
import com.mesutemre.namazvakitleri.core.ext.readString
import com.mesutemre.namazvakitleri.core.ext.saveData
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.TarihteBugunEntity
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity
import com.mesutemre.namazvakitleri.dashboard.domain.model.DashboardVakitPageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*
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

    override suspend fun getVakitInfoOutOfCollection(
        bugun: String,
        yarin: String
    ): List<VakitInfoEntity> {
        return dao.getVakitBugunYarin(bugun, yarin)
    }

    override suspend fun saveTarihteBugunList(vararg tarihteBugunEntity: TarihteBugunEntity) {
        dao.saveTarihteBugunList(*tarihteBugunEntity)
    }

    override suspend fun clearTarihteBugun() {
        dao.clearTarihteBugun()
    }

    override suspend fun getTarihteBugunList(): List<TarihteBugunEntity> = dao.getTarihteBugunList()

    override suspend fun saveDateForTarihteBugun() {
        dataStore.saveData(
            Constants.DataStoreConstants.TARIHTE_BUGUN_KEY,
            SimpleDateFormat("dd.MM.yyyy").format(Date())
        )
    }

    override suspend fun checkTarihteBugunCallAPI(): Boolean {
        return (dataStore.readString(Constants.DataStoreConstants.TARIHTE_BUGUN_KEY, "")
            .first() == SimpleDateFormat("dd.MM.yyyy").format(Date())).not()
    }

    override suspend fun saveVakitPageType(type: DashboardVakitPageType) {
        dataStore.saveData(Constants.DataStoreConstants.VAKIT_TYPE_PAGE, type.type)
    }

    override suspend fun getVakitPageType(): DashboardVakitPageType {
        return (DashboardVakitPageType from dataStore.readInt(
            Constants.DataStoreConstants.VAKIT_TYPE_PAGE,
            DashboardVakitPageType.DEFAULT.type
        ).first())
            ?: DashboardVakitPageType.DEFAULT
    }

    override suspend fun clearVakitInfo() {
        dao.clearVakitInfo()
    }
}
