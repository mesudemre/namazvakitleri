package com.mesutemre.namazvakitleri.dashboard.data.remote

import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.VakitInfoDto
import retrofit2.Response
import javax.inject.Inject

class DashboardRemoteDataSource @Inject constructor(
    private val api: IDashboardApi
) : IDashboardRemoteDataSource {

    override suspend fun getVakitListe(ilceId: String): Response<List<VakitInfoDto>> =
        api.getVakitListe(ilceId)
}