package com.jarvis.weatherapp.base.datasource

data class DataResponse<T>(val status: Status, val data: T?, val message: String?, val statusCode: Int? = null) {
    companion object {
        fun <T> success(data: T, statusCode: Int? = null): DataResponse<T> =
            DataResponse(status = Status.SUCCESS, data = data, message = null, statusCode = statusCode)

        fun <T> error(message: String, data: T? = null, statusCode: Int? = null): DataResponse<T> =
            DataResponse(status = Status.ERROR, data = data, message = message, statusCode = statusCode)
    }
}


enum class Status {
    SUCCESS,
    ERROR
}