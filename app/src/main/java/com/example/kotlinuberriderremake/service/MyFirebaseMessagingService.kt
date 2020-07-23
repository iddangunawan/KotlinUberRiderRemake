package com.example.kotlinuberriderremake.service

import com.example.kotlinuberriderremake.Common
import com.example.kotlinuberriderremake.utils.UserUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

/**
 * Created by iddangunawan on 15/07/20
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        if (FirebaseAuth.getInstance().currentUser != null) {
            UserUtils.updateToken(this, token)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val data = remoteMessage.data
        if (data != null) {
            Common.showNotification(
                this,
                Random.nextInt(),
                data[Common.NOTIFICATION.TITLE],
                data[Common.NOTIFICATION.BODY],
                null
            )
        }
    }
}