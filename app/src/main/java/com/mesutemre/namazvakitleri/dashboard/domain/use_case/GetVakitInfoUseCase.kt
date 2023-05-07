package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoData
import com.mesutemre.namazvakitleri.di.IoDispatcher
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ViewModelScoped
class GetVakitInfoUseCase @Inject constructor(
    private val dashboardRepository: IDashboardRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<VakitInfoData>> {
        val vakitListExist = dashboardRepository.checkVakitListSaved().first()
        return if (vakitListExist) {
            dashboardRepository.getVakitInfo(SimpleDateFormat("dd.MM.yyyy").format(Date()))
        } else {
            val district = dashboardRepository.getSelectedDistrictFromStore()
            val response = dashboardRepository.getVakitListe(district.districtId.toString())
            withContext(ioDispatcher) {
                async {
                    response.collectLatest { res ->
                        if (res is BaseResourceEvent.Success) {
                            res.data?.let { list ->
                                dashboardRepository.saveVakitListToDb(list)
                            }
                            dashboardRepository.saveCheckVakitListSaved(true)
                        }
                    }
                }.await()
                dashboardRepository.getVakitInfo(SimpleDateFormat("dd.MM.yyyy").format(Date()))
            }
        }
    }
}