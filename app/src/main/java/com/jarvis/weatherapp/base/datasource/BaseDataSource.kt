package com.jarvis.weatherapp.base.datasource

import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): DataResponse<T> {
        return try {
            val response = call()
            val statusCode = response.code()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return DataResponse.success(body, statusCode)
                }
            }
            error(response.message(), null, statusCode = statusCode)
        } catch (e: Exception) {
            error(e.message ?: e.toString(), e.javaClass.canonicalName)
        }
    }

    private fun <T> error(message: String, canonicalName: String?, statusCode: Int? = null): DataResponse<T> {
        canonicalName?.let {
            if (it.contains("SocketTimeoutException")) {
                return DataResponse.error("Network call has failed for a following reason: Cannot access", statusCode = statusCode)
            }
        }
        return DataResponse.error("Network call has failed for a following reason: $message", statusCode = statusCode)
    }
}