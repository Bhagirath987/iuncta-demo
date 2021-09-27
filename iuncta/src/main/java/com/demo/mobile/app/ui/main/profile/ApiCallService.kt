package com.demo.mobile.app.ui.main.profile

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ApiCallService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY;
        Log.e(">>>>", "onStartCommand: " )
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.e(">>>>", "onBind: " )
        return null
    }
}