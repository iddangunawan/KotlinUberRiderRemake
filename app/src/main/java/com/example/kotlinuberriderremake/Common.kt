package com.example.kotlinuberriderremake

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.kotlinuberriderremake.model.DriverGeoModel
import com.example.kotlinuberriderremake.model.RiderInfoModel
import com.google.android.gms.maps.model.Marker

/**
 * Created by iddangunawan on 17/07/20
 */
object Common {
    val TOKEN_REFERENCE: String = "Token"
    val RIDER_INFO_REFERENCE: String = "RiderInfo"
    val DRIVER_INFO_REFERENCE: String = "DriverInfo"
    val DRIVERS_LOCATION_REFERENCES: String = "DriversLocation" // same as Server app

    var currentRider: RiderInfoModel? = null
    val markerList: MutableMap<String, Marker>? = HashMap()
    val driversFound: MutableSet<DriverGeoModel>? = HashSet()

    fun buildWelcomeMessage(): String {
        return if (currentRider != null)
            StringBuilder("Welcome ")
                .append(currentRider?.firstName)
                .append(" ")
                .append(currentRider?.lastName).toString()
        else
            ""
    }

    fun buildName(firstName: String?, lastName: String?): String? {
        return StringBuilder(firstName!!).append(" ").append(lastName).toString()
    }

    fun showNotification(context: Context, id: Int, title: String?, body: String?, intent: Intent?) {
        var pendingIntent: PendingIntent? = null

        if (intent != null) {
            pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val NOTIFICATION_CHANNEL_ID = "mylektop_uber_remake"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Uber Remake",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "Uber Remake"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_VIBRATE)
            .setSmallIcon(R.drawable.ic_car_black_24dp)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_car_black_24dp
                )
            )

        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent)
        }

        val notification = builder.build()
        notificationManager.notify(id, notification)
    }

    object NOTIFICATION {
        val TITLE: String = "title"
        val BODY: String = "body"
    }
}