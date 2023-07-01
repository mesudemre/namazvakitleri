package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ClearVakitInfo @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke() {
        dashboardRepository.clearVakitInfo()
    }
}