package com.mesutemre.namazvakitleri.core.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DbCall : IDBCall {

    override fun <T : Any> dbCall(call: suspend () -> T): Flow<BaseResourceEvent<T>> = flow {
        emit(BaseResourceEvent.Loading())
        var response: T? = null
        try {
            response = call()
        } catch (t: Throwable) {
            emit(BaseResourceEvent.Error(t.message!!))
        }
        if (response == null) {
            emit(BaseResourceEvent.Error("Herhangi bir data bulunamadı!"))
        } else {
            emit(BaseResourceEvent.Success(response))
        }
    }
}