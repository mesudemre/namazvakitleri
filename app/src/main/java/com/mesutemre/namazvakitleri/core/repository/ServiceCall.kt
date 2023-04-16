package com.mesutemre.namazvakitleri.core.repository

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ServiceCall : IServiceCall {

    override fun <T : Any> serviceCall(call: suspend () -> Response<T>): Flow<BaseResourceEvent<T>> =
        flow {
            emit(BaseResourceEvent.Loading())
            var response: Response<T>? = null
            try {
                response = call()
            } catch (t: Throwable) {
                emit(BaseResourceEvent.Error(t.message!!))
            }
            if (response != null) {
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()
                    emit(BaseResourceEvent.Error(errorBody.toString()))
                } else {
                    emit(BaseResourceEvent.Success(response.body()!!))
                }
            }
        }
}