package com.demo.mobile.app.util.services

import android.annotation.SuppressLint
import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class FireBaseServices : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        /*val gcmRec = Intent(Constants.NotificationReceived)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(gcmRec)
        selectPushType(remoteMessage)
        Timber.e(" ${remoteMessage.data}")*/
    }

    override fun onNewToken(token: String) {
        /*Timber.e(token)
        Prefs.putString(Constants.FIREBSAE_TOKEN, token)
        super.onNewToken(token)*/
    }

    private fun selectPushType(remoteMessage: RemoteMessage) {
        NotificationUtils.initNotification(applicationContext).sendNotification(
            remoteMessage.data["title"].toString(), remoteMessage.data["body"].toString(),
            getNotificationIntent(remoteMessage), Random.nextInt(5000)
        )
    }

    @SuppressLint("LogNotTimber")
    private fun getNotificationIntent(remoteMessage: RemoteMessage): Intent {
        /*if (!remoteMessage.data["type"].isNullOrEmpty()) {
            when (remoteMessage.data["type"]?.toInt()) {
                Constants.TYPE_MATCH -> {
                    Log.e("TYPE_MATCH", "TYPE_MATCH")
                    val intent = Intent(baseContext, UserProfileDetailsActivity::class.java)
                    intent.putExtra("UserID", remoteMessage.data["userId"].toString())
                    intent.putExtra("fromNotification", true)
                    return intent
                }
                Constants.TYPE_CHAT -> {
                    Log.e("TYPE_CHAT", "TYPE_CHAT")
                    val user = UsersItem(
                        0,
                        remoteMessage.data["sender"]?.toInt() ?: 0,
                        remoteMessage.data["receiver"]?.toInt() ?: 0,
                        remoteMessage.data["id"]?.toInt() ?: 0,
                        "", remoteMessage.data["firstname"].toString(),
                        "", "", remoteMessage.data["profile"].toString(),
                        0, "", 0, 0
                    )
                    val intent = Intent(baseContext, ChatProfileActivity::class.java)
                    intent.putExtra("lastseen", 0)
                    intent.putExtra("dataObjInbox", user)
                    return intent
                }
                Constants.TYPE_DM -> {
                    Log.e("TYPE_DM", "TYPE_DM")
                    val user = com.shipo.matic.app.data.beans.inboxchat.UsersItem(
                        0,
                        remoteMessage.data["sender"]?.toInt() ?: 0,
                        remoteMessage.data["firstname"].toString(),
                        0, remoteMessage.data["receiver"]?.toInt() ?: 0,
                        remoteMessage.data["profile"].toString(),
                        "", "", 0, 0, ""
                    )
                    val intent = Intent(baseContext, ChatProfileActivity::class.java)
                    intent.putExtra("lastseen", 0)
                    intent.putExtra("dataObj", user)
                    return intent
                }
                Constants.TYPE_OFF -> {
                    Log.e("TYPE_OFF", "TYPE_OFF")
                    return MainActivity.newIntent(applicationContext)
                }
                Constants.TYPE_ADMIN -> {
                    Log.e("TYPE_ADMIN", "TYPE_ADMIN")
                    return MainActivity.newIntent(applicationContext)
                }
                else -> {
                    return MainActivity.newIntent(applicationContext)
                }
            }
        } else {
            return MainActivity.newIntent(applicationContext)
        }*/

        return Intent()
    }
}