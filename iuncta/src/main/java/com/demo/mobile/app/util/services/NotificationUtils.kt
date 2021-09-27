package com.demo.mobile.app.util.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.demo.mobile.app.R


class NotificationUtils {


    private var notificationBuilder: Notification.Builder? = null
    private var notificationManager: NotificationManager? = null

    companion object {
        private var context: Context? = null

        fun initNotification(context: Context?): NotificationUtils {
            this.context = context
            return NotificationUtils()
        }

    }


    fun sendNotification(title: String, message: String, notificationIntent: Intent?, id: Int) {
        notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var pendingIntent: PendingIntent? = null
        if (notificationIntent != null) {
            notificationIntent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            val iUniqueId = (System.currentTimeMillis() and 0xfffffff).toInt()
            pendingIntent = PendingIntent.getActivity(context, iUniqueId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        notificationBuilder = Notification.Builder(context)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker(title)
            .setStyle(Notification.BigTextStyle().bigText(message))
            .setAutoCancel(true)


        if (notificationIntent != null) {
            notificationBuilder?.setContentIntent(pendingIntent)
        }

        notificationBuilder?.setColor(Color.TRANSPARENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelComment("Comment_Notifications", "Comment Notifications")
        }

        notificationManager?.notify(id, (notificationBuilder ?: return).build())

    }

    private fun setWindowManagerCustom(materialDialog: Dialog) {
        val layOutParams = WindowManager.LayoutParams()
        layOutParams.copyFrom((materialDialog.window ?: return).attributes)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun channelComment(channelId: String, name: String) {
        notificationBuilder?.setChannelId(channelId)
        val description = context?.getString(R.string.app_name)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(channelId, name, importance)
        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = Color.parseColor("#3ecafc")
        mChannel.enableVibration(true)
        notificationManager?.createNotificationChannel(mChannel)
    }

}