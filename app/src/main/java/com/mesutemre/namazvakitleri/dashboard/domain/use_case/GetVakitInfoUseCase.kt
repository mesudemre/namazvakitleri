package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoScreenData
import com.mesutemre.namazvakitleri.di.IoDispatcher
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
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
    suspend operator fun invoke(): Flow<BaseResourceEvent<VakitInfoScreenData>> = channelFlow {
        send(BaseResourceEvent.Loading())
        val bugunTar = SimpleDateFormat("dd.MM.yyyy").format(Date())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val yarinTar = SimpleDateFormat("dd.MM.yyyy").format(calendar.time)
        val vakitListExist = dashboardRepository.checkVakitListSaved().first()
        if (vakitListExist) {
            val vakitList = dashboardRepository.getVakitInfoOutOfCollection(bugunTar, yarinTar)
            if (vakitList.size < 2) {
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
                    val vakitList =
                        dashboardRepository.getVakitInfoOutOfCollection(bugunTar, yarinTar)
                    send(
                        BaseResourceEvent.Success(
                            data = VakitInfoScreenData(
                                bugunVakitInfo = vakitList[0],
                                yarinVakitInfo = vakitList[1]
                            )
                        )
                    )
                }
            } else {
                send(
                    BaseResourceEvent.Success(
                        data = VakitInfoScreenData(
                            bugunVakitInfo = vakitList[0],
                            yarinVakitInfo = vakitList[1]
                        )
                    )
                )
            }
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
            }
            val vakitList =
                dashboardRepository.getVakitInfoOutOfCollection(bugunTar, yarinTar)
            send(
                BaseResourceEvent.Success(
                    data = VakitInfoScreenData(
                        bugunVakitInfo = vakitList[0],
                        yarinVakitInfo = vakitList[1]
                    )
                )
            )
        }
    }
}