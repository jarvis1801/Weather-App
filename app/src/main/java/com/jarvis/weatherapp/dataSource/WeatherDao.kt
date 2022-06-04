package com.jarvis.weatherapp.dataSource

import androidx.room.*
import com.jarvis.weatherapp.model.WeatherResponse

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getAll(): List<WeatherResponse>?

    @Query("SELECT * FROM weather WHERE id = :id")
    fun getById(id: String?): WeatherResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<WeatherResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: WeatherResponse)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: WeatherResponse)

    @Query("UPDATE weather SET updatedAt =:updatedAt WHERE id = :id")
    fun updateUpdateAt(id: Int, updatedAt: Long): Int

    @Query("SELECT * FROM weather ORDER BY updatedAt DESC LIMIT 1")
    fun getLastSearch(): WeatherResponse?

    @Delete
    fun delete(weatherResponse: WeatherResponse)
}