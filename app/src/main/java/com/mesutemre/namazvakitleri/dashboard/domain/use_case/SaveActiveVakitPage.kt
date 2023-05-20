package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.model.DashboardVakitPageType
import javax.inject.Inject

class SaveActiveVakitPage @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke(type: DashboardVakitPageType) {
        dashboardRepository.saveVakitPageType(type)
    }
}