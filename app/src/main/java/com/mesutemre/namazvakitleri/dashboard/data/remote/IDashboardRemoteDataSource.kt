package com.mesutemre.namazvakitleri.dashboard.data.remote

import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.TarihteBugunDto
import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.VakitInfoDto
import retrofit2.Response

interface IDashboardRemoteDataSource {

    suspend fun getVakitListe(ilceId: String): Response<List<VakitInfoDto>>
    suspend fun getTarihteBugun(): Response<TarihteBugunDto>
}