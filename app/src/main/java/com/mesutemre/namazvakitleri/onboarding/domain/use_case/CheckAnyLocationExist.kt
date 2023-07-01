package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CheckAnyLocationExist @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke(): Boolean {
        val savedDistrict = dashboardRepository.getSelectedDistrictFromStore()
        return savedDistrict?.let {
            true
        } ?: run {
            false
        }
    }
}