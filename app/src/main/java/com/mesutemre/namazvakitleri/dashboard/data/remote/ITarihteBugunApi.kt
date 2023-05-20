package com.mesutemre.namazvakitleri.dashboard.data.remote

import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.TarihteBugunDto
import retrofit2.Response
import retrofit2.http.GET

interface ITarihteBugunApi {

    @GET("/tarihtebugun")
    suspend fun getTarihteBugun(): Response<TarihteBugunDto>
}