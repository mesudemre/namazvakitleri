package com.mesutemre.namazvakitleri.dashboard.data.local

import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity
import kotlinx.coroutines.flow.Flow

interface IDashboardLocalDataSource {

    suspend fun getVakitInfo(tarih: String): VakitInfoEntity
    suspend fun saveVakitInfo(vararg vakitInfoEntity: VakitInfoEntity)
    suspend fun checkVakitListSaved(): Flow<Boolean>
    suspend fun saveCheckVakitListSaved(isExist: Boolean)
}