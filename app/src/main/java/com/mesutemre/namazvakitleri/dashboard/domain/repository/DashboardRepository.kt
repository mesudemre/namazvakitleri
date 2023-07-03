package com.mesutemre.namazvakitleri.dashboard.domain.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.core.repository.BaseRepository
import com.mesutemre.namazvakitleri.dashboard.data.local.IDashboardLocalDataSource
import com.mesutemre.namazvakitleri.dashboard.data.mapper.TarihteBugunDataMapper
import com.mesutemre.namazvakitleri.dashboard.data.mapper.VakitDataMapper
import com.mesutemre.namazvakitleri.dashboard.data.remote.IDashboardRemoteDataSource
import com.mesutemre.namazvakitleri.dashboard.data.repository.IDashboardRepository
import com.mesutemre.namazvakitleri.dashboard.domain.model.DashboardVakitPageType
import com.mesutemre.namazvakitleri.dashboard.domain.model.TarihteBugunData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoData
import com.mesutemre.namazvakitleri.di.IoDispatcher
import com.mesutemre.namazvakitleri.onboarding.data.local.IOnboardingLocalDataSource
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.*
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val dashboardLocalDataSource: IDashboardLocalDataSource,
    private val dashboardRemoteDataSource: IDashboardRemoteDataSource,
    private val onboardingLocalDataSource: IOnboardingLocalDataSource,
    private val vakitDataMapper: VakitDataMapper,
    private val tarihteBugunDataMapper: TarihteBugunDataMapper
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

    override suspend fun getSelectedDistrictFromStore(): DistrictData? =
        onboardingLocalDataSource.getSelectedDistrictFromDataStore()

    override suspend fun getVakitInfoOutOfCollection(
        bugun: String,
        yarin: String
    ): List<VakitInfoData> {
        val list = dashboardLocalDataSource.getVakitInfoOutOfCollection(bugun, yarin)
        return list.map {
            vakitDataMapper.convertVakitInfoEntityToVakitInfoData(it)
        }
    }

    override suspend fun saveTarihteBugunTarih() {
        dashboardLocalDataSource.saveDateForTarihteBugun()
    }

    override suspend fun checkTarihteBugunCallAPI(): Boolean =
        dashboardLocalDataSource.checkTarihteBugunCallAPI()

    override suspend fun getTarihteBugunListFromAPI(): Flow<BaseResourceEvent<List<TarihteBugunData>>> {
        return callApi(
            call = {
                dashboardRemoteDataSource.getTarihteBugun()
            },
            mapperCall = {
                it.itemList.map {
                    tarihteBugunDataMapper.convertTarihteBugunDtoToTarihteBugunData(it)
                }
            }
        )
    }

    override suspend fun getTarihteBugunListFromDB(): Flow<BaseResourceEvent<List<TarihteBugunData>>> {
        return callDb(
            call = {
                dashboardLocalDataSource.getTarihteBugunList()
            },
            mapperCall = {
                it.map { tarihteBugunEntity ->
                    tarihteBugunDataMapper.convertTarihteBugunEntityToTarihteBugunData(
                        tarihteBugunEntity
                    )
                }
            }
        )
    }

    override suspend fun saveTarihteBugunListToDb(tarihteBugunList: List<TarihteBugunData>) {
        dashboardLocalDataSource.clearTarihteBugun()
        dashboardLocalDataSource.saveTarihteBugunList(
            *(
                    tarihteBugunList.map {
                        tarihteBugunDataMapper.convertTarihteBugunDataToTarihteBugunEntity(it)
                    }).toTypedArray()
        )
    }

    override suspend fun saveVakitPageType(type: DashboardVakitPageType) {
        dashboardLocalDataSource.saveVakitPageType(type)
    }

    override suspend fun getVakitPageType(): DashboardVakitPageType =
        dashboardLocalDataSource.getVakitPageType()

    override suspend fun clearVakitInfo() {
        dashboardLocalDataSource.clearVakitInfo()
    }

    override suspend fun checkCumaSnackBarVisibility(): Boolean {
        val calendar = Calendar.getInstance()
        val canCreateMessage = (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                &&
                calendar.get(Calendar.HOUR_OF_DAY) > 10
        val isMessageVisible = dashboardLocalDataSource.checkCumaSnackBarVisibility().first()
        return canCreateMessage && isMessageVisible
    }

    override suspend fun saveStateOfCumaSnackBarMessage(visibility: Boolean) {
        dashboardLocalDataSource.saveStateOfCumaSnackBarMessage(visibility)
    }
}