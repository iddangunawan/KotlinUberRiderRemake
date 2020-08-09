package com.example.kotlinuberriderremake.model

import com.firebase.geofire.GeoLocation

/**
 * Created by iddangunawan on 06/08/20
 */
class DriverGeoModel {
    var key: String? = null
    var geoLocation: GeoLocation? = null
    var driverInfoModel: DriverInfoModel? = null

    constructor(key: String?, geoLocation: GeoLocation?) {
        this.key = key
        this.geoLocation = geoLocation!!
    }
}