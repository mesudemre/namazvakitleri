package com.mesutemre.namazvakitleri.worker.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckAndPushNotificationTokenToFirebase @Inject constructor(
    private val onboardingRepository: IOnboardingRepository,
    private val dashboardRepository: IDashboardRepository
) {
    suspend operator fun invoke(
        onWorkComplete:(Boolean) -> Unit
    ){
        val selectedDistrict = dashboardRepository.getSelectedDistrictFromStore()
        selectedDistrict?.let {
            val savedPushToken = onboardingRepository.getSavedPushTokenFromDataStore()
            if (savedPushToken.isNullOrEmpty().not()) {
                onboardingRepository.isTokenExistInFirebase(savedPushToken) {
                    onWorkComplete(it)
                    if (it.not()) {
                        onboardingRepository.saveTokenIlceToFirebase(
                            savedPushToken,
                            selectedDistrict.districtId.toString()
                        )
                    }
                }
            } else {
                onWorkComplete(false)
                onboardingRepository.getAndSaveNotificationToken(it.districtId.toString())
            }
        }
    }
}