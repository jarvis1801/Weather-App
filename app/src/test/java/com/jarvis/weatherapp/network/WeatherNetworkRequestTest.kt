package com.jarvis.weatherapp.network

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class WeatherNetworkRequestTest {

    @Mock
    lateinit var apiService: WeatherService
    lateinit var gson: Gson

    @Before
    fun setup() {
        gson = GsonBuilder().create()
        apiService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WeatherService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun deconstruct() {

    }

    @Test
    fun validateWeatherData_return_success() {
        runBlocking {
            val response = apiService.getWeatherFromCityName("Hong Kong", appId = "95d190a434083879a6398aafd54d9e73")

            assertThat(response.body()?.id).isNotNull()
        }
    }

    @Test
    fun validateWeatherData_return_fail() {
        runBlocking {
            val response = apiService.getWeatherFromCityName("idjfiosfj", appId = "95d190a434083879a6398aafd54d9e73")

            assertThat(response.body()).isNull()
        }
    }

}