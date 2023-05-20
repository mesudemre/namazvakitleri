package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.model.DashboardVakitPageType
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetActiveVakitPage @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke(): DashboardVakitPageType = dashboardRepository.getVakitPageType()
}