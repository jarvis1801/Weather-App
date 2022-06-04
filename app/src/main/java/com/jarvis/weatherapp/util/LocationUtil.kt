package com.jarvis.weatherapp.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import com.jarvis.weatherapp.util.LocationUtil.getFusedLocationClient
import com.jarvis.weatherapp.util.LocationUtil.getLastKnownLocation
import com.jarvis.weatherapp.util.PermissionUtil.checkPermissionIsGranted
import com.jarvis.weatherapp.util.PermissionUtil.locationPermission
import com.jarvis.weatherapp.util.PermissionUtil.requestPermission


object LocationUtil {

    fun Context.getFusedLocationClient(): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(this)
    }

    @SuppressLint("MissingPermission")
    fun Activity.getLastKnownLocation(fusedLocationClient: FusedLocationProviderClient, callback: (location: Location?) -> Unit) {
        if (checkPermissionIsGranted(locationPermission)) {
            fusedLocationClient.lastLocation.addOnSuccessListener(this@getLastKnownLocation) { location ->
                Log.d("chris", "null")
                callback(location)
            }.addOnCanceledListener {
                Log.d("chris", "cancell")
            }.addOnFailureListener {
                it.printStackTrace()
            }
        } else {
            requestPermission(locationPermission)
        }
    }
}