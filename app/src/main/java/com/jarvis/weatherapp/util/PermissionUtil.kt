package com.jarvis.weatherapp.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUtil {

    const val REQUEST_CODE_LOCATION = 1

    val locationPermission = arrayListOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    fun Context.checkPermissionIsGranted(permissionList: ArrayList<String>): Boolean {
        var isGrantAll = false
        var grantedSize = 0
        permissionList.forEach { permission ->
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                grantedSize++
            }
        }
        if (grantedSize == permissionList.size) isGrantAll = true

        return isGrantAll
    }

    fun Activity.shouldRationale(permission: String) {
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
    }

    fun Activity.requestPermission(permissionList: ArrayList<String>) {
        requestPermissions(permissionList.toTypedArray(), REQUEST_CODE_LOCATION)
    }
}