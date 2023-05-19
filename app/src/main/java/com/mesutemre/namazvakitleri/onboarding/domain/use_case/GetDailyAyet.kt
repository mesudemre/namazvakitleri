package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.AyetData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetDailyAyet @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<AyetData>> =
        onboardingRepository.getAyetByDayOfMonth()
}