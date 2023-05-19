package com.mesutemre.namazvakitleri.dashboard.data.local

import com.mesutemre.namazvakitleri.dashboard.data.local.entity.TarihteBugunEntity
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity
import kotlinx.coroutines.flow.Flow

interface IDashboardLocalDataSource {

    suspend fun getVakitInfo(tarih: String): VakitInfoEntity
    suspend fun saveVakitInfo(vararg vakitInfoEntity: VakitInfoEntity)
    suspend fun checkVakitListSaved(): Flow<Boolean>
    suspend fun saveCheckVakitListSaved(isExist: Boolean)
    suspend fun getVakitInfoOutOfCollection(
        bugun: String,
        yarin: String
    ): List<VakitInfoEntity>

    suspend fun saveTarihteBugunList(vararg tarihteBugunEntity: TarihteBugunEntity)
    suspend fun clearTarihteBugun()
    suspend fun getTarihteBugunList(): List<TarihteBugunEntity>
    suspend fun saveDateForTarihteBugun()
    suspend fun checkTarihteBugunCallAPI(): Boolean
}