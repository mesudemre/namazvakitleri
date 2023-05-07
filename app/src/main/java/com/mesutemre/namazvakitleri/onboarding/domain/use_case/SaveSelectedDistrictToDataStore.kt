package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import javax.inject.Inject

class SaveSelectedDistrictToDataStore @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke(districtData: DistrictData) {
        onboardingRepository.saveSelectedDistrictToDataStore(districtData)
    }
}