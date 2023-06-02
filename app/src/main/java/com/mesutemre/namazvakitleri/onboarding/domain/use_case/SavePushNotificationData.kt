package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SavePushNotificationData @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke(ilceId: String) {
        onboardingRepository.getAndSaveNotificationToken(ilceId)
    }
}