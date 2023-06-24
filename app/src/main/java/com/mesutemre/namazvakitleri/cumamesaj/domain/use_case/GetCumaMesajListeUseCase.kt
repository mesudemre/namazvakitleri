package com.mesutemre.namazvakitleri.cumamesaj.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.cumamesaj.data.repository.ICumaMesajRepository
import com.mesutemre.namazvakitleri.cumamesaj.domain.model.CumaMesajData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetCumaMesajListeUseCase @Inject constructor(
    private val cumaMesajRepository: ICumaMesajRepository
) {

    suspend operator fun invoke(): Flow<BaseResourceEvent<List<CumaMesajData>>> =
        cumaMesajRepository.getCumaMesajList()
}