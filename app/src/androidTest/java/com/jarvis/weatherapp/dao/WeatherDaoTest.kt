package com.jarvis.weatherapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jarvis.weatherapp.dataSource.AppDatabase
import com.jarvis.weatherapp.dataSource.WeatherDao
import com.jarvis.weatherapp.model.WeatherResponse
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {
    private lateinit var weatherDao: WeatherDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testLastSearchValid() {
        val weatherList: List<WeatherResponse> = DaoTestData.createDummyWeather()
        weatherDao.insertAll(weatherList)
        val lastSearch = weatherDao.getLastSearch()
        val exceptedLastSearch = weatherList.maxByOrNull { it.updatedAt ?: 0 }
        MatcherAssert.assertThat(lastSearch, IsEqual.equalTo(exceptedLastSearch))
    }
}