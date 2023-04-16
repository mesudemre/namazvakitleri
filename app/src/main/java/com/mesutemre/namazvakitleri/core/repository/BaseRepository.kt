package com.mesutemre.namazvakitleri.core.repository

import android.util.Log
import com.mesutemre.namazvakitleri.core.ext.convertRersourceEventType
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IServiceCall by ServiceCall(), IDBCall by DbCall() {

    open fun <T : Any,C: Any> callApi(
        call: suspend () -> Response<T>,
        mapperCall: (T) -> C
    ): Flow<BaseResourceEvent<C>> {
        return serviceCall {
            call()
        }.map {
            it.convertRersourceEventType {
                mapperCall(it.data!!)
            }
        }.flowOn(ioDispatcher)
    }

    open fun <T : Any,C: Any> callDb(
        call: suspend () -> T,
        mapperCall: (T) -> C
    ): Flow<BaseResourceEvent<C>> {
        return dbCall {
            call()
        }.map {
            it.convertRersourceEventType {
                mapperCall(it.data!!)
            }
        }.flowOn(ioDispatcher)
    }

    open fun <T : Any> callDb(
        call: suspend () -> T
    ){
         dbCall {
            call.invoke()
        }.flowOn(ioDispatcher)
    }
}