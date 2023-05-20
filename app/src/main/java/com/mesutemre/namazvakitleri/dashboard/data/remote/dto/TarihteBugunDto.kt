package com.mesutemre.namazvakitleri.dashboard.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TarihteBugunDto(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("status")
    val status: String,

    @SerializedName("pagecreatedate")
    val pageCreateDate: String,

    @SerializedName("tarihtebugun")
    val itemList: List<TarihteBugunItemDto>
)
