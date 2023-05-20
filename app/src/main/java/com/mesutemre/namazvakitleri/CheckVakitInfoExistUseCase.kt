package com.mesutemre.namazvakitleri

import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckVakitInfoExistUseCase @Inject constructor(
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return dashboardRepository.checkVakitListSaved()
    }
}