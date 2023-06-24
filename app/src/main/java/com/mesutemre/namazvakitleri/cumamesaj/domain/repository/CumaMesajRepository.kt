package com.mesutemre.namazvakitleri.cumamesaj.domain.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.core.repository.BaseRepository
import com.mesutemre.namazvakitleri.cumamesaj.data.mapper.CumaMesajDataMapper
import com.mesutemre.namazvakitleri.cumamesaj.data.remote.ICumaMesajRemoteDataSource
import com.mesutemre.namazvakitleri.cumamesaj.data.remote.dto.CumaMesajDto
import com.mesutemre.namazvakitleri.cumamesaj.data.repository.ICumaMesajRepository
import com.mesutemre.namazvakitleri.cumamesaj.domain.model.CumaMesajData
import com.mesutemre.namazvakitleri.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CumaMesajRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val cumaMesajRemoteDataSource: ICumaMesajRemoteDataSource,
    private val cumaMesajDataMapper: CumaMesajDataMapper
) : BaseRepository(ioDispatcher), ICumaMesajRepository {

    override suspend fun getCumaMesajList(): Flow<BaseResourceEvent<List<CumaMesajData>>> {
        return callFirebase(
            call = {
                cumaMesajRemoteDataSource.getCumaMesajListFromFirebase()
            },
            mapperCall = {
                val list = mutableListOf<CumaMesajData>()
                for (it in it.children) {
                    it.getValue(CumaMesajDto::class.java)?.let { data ->
                        list.add(
                            cumaMesajDataMapper.convertCumaMesajDtoToCumaMesajData(data)
                        )
                    }
                }
                list
            }
        )
    }
}