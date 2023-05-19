package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.model.TarihteBugunData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@ViewModelScoped
class GetTarihteBugunList @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<List<TarihteBugunData>>> {
        val mustCallApi = dashboardRepository.checkTarihteBugunCallAPI()
        return if (mustCallApi) {
            val list = dashboardRepository.getTarihteBugunListFromAPI()
            list.collectLatest {
                if (it is BaseResourceEvent.Success) {
                    it.data?.let { tarihteBugunListe ->
                        dashboardRepository.saveTarihteBugunListToDb(tarihteBugunListe)
                        dashboardRepository.saveTarihteBugunTarih()
                    }
                }
            }
            list
        } else {
            dashboardRepository.getTarihteBugunListFromDB()
        }
    }
}