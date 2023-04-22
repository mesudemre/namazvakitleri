package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetDistrictListByCityId @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke(cityId: Int): Flow<BaseResourceEvent<List<DistrictData>>> {
        val isDistrictListSavedBefore = onboardingRepository.isDistrictListSavedBefore(cityId)
        return if (isDistrictListSavedBefore) {
            onboardingRepository.getDistrictListFromDBByCityId(cityId)
        } else {
            val response = onboardingRepository.getDistrictListFromAPI(cityId)
            response.collectLatest {
                if (it is BaseResourceEvent.Success) {
                    it.data?.let { list ->
                        onboardingRepository.saveDistrict(list, cityId)
                    }
                }
            }
            response
        }
    }
}