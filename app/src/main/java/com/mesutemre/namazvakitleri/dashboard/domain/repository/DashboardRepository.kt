package com.mesutemre.namazvakitleri.dashboard.domain.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.core.repository.BaseRepository
import com.mesutemre.namazvakitleri.dashboard.data.local.IDashboardLocalDataSource
import com.mesutemre.namazvakitleri.dashboard.data.mapper.VakitDataMapper
import com.mesutemre.namazvakitleri.dashboard.data.remote.IDashboardRemoteDataSource
import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoData
import com.mesutemre.namazvakitleri.di.IoDispatcher
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val dashboardLocalDataSource: IDashboardLocalDataSource,
    private val dashboardRemoteDataSource: IDashboardRemoteDataSource,
    private val onboardingLocalDataSource: IOnboardingLocalDataSource,
    private val vakitDataMapper: VakitDataMapper
) : BaseRepository(ioDispatcher), IDashboardRepository {

    override suspend fun getVakitListe(ilceId: String): Flow<BaseResourceEvent<List<VakitInfoData>>> {
        return callApi(
            call = {
                dashboardRemoteDataSource.getVakitListe(ilceId)
            },
            mapperCall = {
                it.map { data ->
                    vakitDataMapper.convertVakitDtoToVakitData(data)
                }
            }
        )
    }

    override suspend fun getVakitInfo(tarih: String): Flow<BaseResourceEvent<VakitInfoData>> {
        return callDb(
            call = {
                dashboardLocalDataSource.getVakitInfo(tarih)
            },
            mapperCall = {
                vakitDataMapper.convertVakitInfoEntityToVakitInfoData(it)
            }
        )
    }

    override suspend fun checkVakitListSaved(): Flow<Boolean> =
        dashboardLocalDataSource.checkVakitListSaved()

    override suspend fun saveCheckVakitListSaved(isExist: Boolean) {
        dashboardLocalDataSource.saveCheckVakitListSaved(isExist)
    }

    override suspend fun saveVakitListToDb(vakitList: List<VakitInfoData>) {
        dashboardLocalDataSource.saveVakitInfo(
            *(
                    vakitList.map {
                        vakitDataMapper.convertVakitInfoDataToVakitInfoEntity(it)
                    }).toTypedArray()
        )
    }

    override suspend fun getSelectedDistrictFromStore(): DistrictData =
        onboardingLocalDataSource.getSelectedDistrictFromDataStore()
}