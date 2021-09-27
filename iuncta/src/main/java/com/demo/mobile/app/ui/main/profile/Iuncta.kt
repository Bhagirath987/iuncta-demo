package com.demo.mobile.app.ui.main.profile

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.ui.main.MyService
//please continue frome here
//https://www.truiton.com/2014/11/bound-service-example-android/
class Iuncta {
    var mBoundService: MyService? = null
    var mServiceBound: Boolean = false
    public fun setSecret(key: String, token: String, context: Context) {
        Constants.KEY.SECRET_KEY = key
        Constants.KEY.SECRET_TOKEN = token
        val i = Intent(context, MyService::class.java)
        context.startService(i)
        context.bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    public fun callCreateTokenApi() {
        val data = HomeActivity()
        data.accessTokenApi()
    }
    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.getService()
            mServiceBound = true
        }
    }
}