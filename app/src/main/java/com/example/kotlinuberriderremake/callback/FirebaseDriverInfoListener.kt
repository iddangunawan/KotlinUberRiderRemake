package com.example.kotlinuberriderremake.callback

import com.example.kotlinuberriderremake.model.DriverGeoModel

/**
 * Created by iddangunawan on 06/08/20
 */
interface FirebaseDriverInfoListener {
    fun onDriverInfoLoadSuccess(driverGeoModel: DriverGeoModel?)
}