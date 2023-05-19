package com.mesutemre.namazvakitleri.dashboard.data.remote

import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.VakitInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IDashboardApi {

    @GET("vakitler/{ilceId}")
    suspend fun getVakitListe(@Path("ilceId") ilceId: String): Response<List<VakitInfoDto>>

}