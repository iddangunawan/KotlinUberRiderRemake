package com.example.kotlinuberriderremake

import com.example.kotlinuberriderremake.model.RiderInfoModel

/**
 * Created by iddangunawan on 17/07/20
 */
object Common {
    val RIDER_INFO_REFERENCE: String = "RiderInfo"

    var currentRider: RiderInfoModel? = null

    fun buildWelcomeMessage(): String {
        return if (currentRider != null)
            StringBuilder("Welcome ")
                .append(currentRider?.firstName)
                .append(" ")
                .append(currentRider?.lastName).toString()
        else
            ""
    }
}