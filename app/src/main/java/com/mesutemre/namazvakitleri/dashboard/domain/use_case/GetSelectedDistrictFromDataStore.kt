package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetSelectedDistrictFromDataStore @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke(): DistrictData? {
        return dashboardRepository.getSelectedDistrictFromStore()
    }
}