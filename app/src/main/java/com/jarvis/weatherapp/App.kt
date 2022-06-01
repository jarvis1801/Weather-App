package com.jarvis.weatherapp

import android.app.Application
import android.content.Context
import java.io.IOException

class App : Application() {

    companion object {
        lateinit var instance: App
//        @JvmStatic lateinit var database: AppDatabase
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

//        database = Room.databaseBuilder(this, AppDatabase::class.java, applicationContext.packageName)
////            .addMigrations(MIGRATION_1_2)
//            .build()

    }

    fun getStringFromAsset(fileName: String) : String? {
        try {
            val inputStream = applicationContext.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer)
        } catch (e: IOException) { e.printStackTrace() }

        return null
    }
}