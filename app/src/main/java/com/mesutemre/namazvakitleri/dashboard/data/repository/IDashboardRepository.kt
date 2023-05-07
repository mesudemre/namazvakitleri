package com.mesutemre.namazvakitleri.dashboard.data.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import kotlinx.coroutines.flow.Flow

interface IDashboardRepository {

    suspend fun getVakitListe(ilceId: String): Flow<BaseResourceEvent<List<VakitInfoData>>>
    suspend fun getVakitInfo(tarih: String): Flow<BaseResourceEvent<VakitInfoData>>
    suspend fun checkVakitListSaved(): Flow<Boolean>
    suspend fun saveCheckVakitListSaved(isExist: Boolean)
    suspend fun saveVakitListToDb(vakitList: List<VakitInfoData>)
    suspend fun getSelectedDistrictFromStore(): DistrictData
}