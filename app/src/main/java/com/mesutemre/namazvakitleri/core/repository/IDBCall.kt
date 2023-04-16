package com.mesutemre.namazvakitleri.core.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import kotlinx.coroutines.flow.Flow

interface IDBCall {

    fun <T : Any> dbCall(
        call: suspend () -> T
    ): Flow<BaseResourceEvent<T>>
}