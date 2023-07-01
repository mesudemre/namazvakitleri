package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteHadisList @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke() {
        onboardingRepository.deleteHadisList()
    }
}