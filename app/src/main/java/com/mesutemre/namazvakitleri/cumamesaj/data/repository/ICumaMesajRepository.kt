package com.mesutemre.namazvakitleri.cumamesaj.data.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.cumamesaj.domain.model.CumaMesajData
import kotlinx.coroutines.flow.Flow

interface ICumaMesajRepository {

    suspend fun getCumaMesajList(): Flow<BaseResourceEvent<List<CumaMesajData>>>
}