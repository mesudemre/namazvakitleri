package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import dagger.hilt.android.scopes.ViewModelScoped
import java.util.*
import javax.inject.Inject

@ViewModelScoped
class SaveCumaSnackMessageState @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke() {
        val calendar = Calendar.getInstance()
        val isCuma = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
        if (isCuma.not()) {
            dashboardRepository.saveStateOfCumaSnackBarMessage(true)
        } else {
            setCumaMessageState()
        }
    }

    private suspend fun setCumaMessageState() {
        val value = dashboardRepository.checkCumaSnackBarVisibility()
        if (value) {
            dashboardRepository.saveStateOfCumaSnackBarMessage(false)
        }
    }
}