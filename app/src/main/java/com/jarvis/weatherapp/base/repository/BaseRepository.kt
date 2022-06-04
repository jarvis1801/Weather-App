package com.jarvis.weatherapp.base.repository

import com.jarvis.weatherapp.base.datasource.DataResponse
import com.jarvis.weatherapp.base.datasource.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    protected suspend fun <A> performRequest(networkCall: suspend () -> DataResponse<A>)
        : DataResponse<A> = withContext(Dispatchers.IO) {

        val response : DataResponse<A> = networkCall.invoke()
        val statusCode = response.statusCode
        when (response.status) {
            Status.SUCCESS -> {
                val data = response.data!!
                return@withContext DataResponse.success(data, statusCode)
            }
            Status.ERROR -> {
                val error = response.message
                return@withContext DataResponse.error(error ?: "", null, statusCode)
            }
            else -> {
                val error = response?.message
                return@withContext DataResponse.error(error ?: "", null, statusCode)
            }
        }
    }
}