package com.jarvis.weatherapp.network

import com.jarvis.weatherapp.App
import com.jarvis.weatherapp.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        val BASE_URL = App.instance.resources.getString(R.string.API_DOMAIN)
        val API_KEY = App.instance.resources.getString(R.string.API_KEY)
        const val TIME_OUT = 3L
    }

    inline fun <reified T> getService(): T = getRetrofitClient().create(T::class.java)

    fun getRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient() : OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(getLoggingInterceptor())
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)

        return builder.build()
    }

    private fun getLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
}