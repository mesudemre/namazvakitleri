package com.mesutemre.namazvakitleri.core.repository

import android.accounts.NetworkErrorException
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.ConnectException

class ServiceCall : IServiceCall {

    override fun <T : Any> serviceCall(call: suspend () -> Response<T>): Flow<BaseResourceEvent<T>> =
        flow {
            emit(BaseResourceEvent.Loading())
            var response: Response<T>? = null
            try {
                response = call()
            } catch (exception: Exception) {
                when (exception) {
                    is ConnectException -> {
                        emit(BaseResourceEvent.Error(exception.message!!))
                    }
                    is NetworkErrorException -> {
                        emit(BaseResourceEvent.Error(exception.message!!))
                    }
                    else -> {
                        emit(BaseResourceEvent.Error(exception.message!!))
                    }
                }

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