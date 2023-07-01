package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteAyetList @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke() {
        onboardingRepository.deleteAyetList()
    }
}