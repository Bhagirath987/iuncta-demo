package com.demo.mobile.app.ui.main.profile

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.ui.main.MyService
import com.demo.mobile.app.ui.main.callback.CreateTokenCallBack
import com.demo.mobile.app.ui.main.callback.LoginWithKeyCallBack


class Iuncta {
    var mBoundService: MyService? = null
    var userToken: String = ""
    var key: String = ""
    var mServiceBound: Boolean = false
    var createTokenCallback: CreateTokenCallBack? = null
    var loginWithKeyCallBack: LoginWithKeyCallBack? = null

    fun setSecret(
        key: String,
        token: String,
        context: Context,
        createTokenCallback: CreateTokenCallBack
    ) {
        this.createTokenCallback = createTokenCallback
        Constants.KEY.SECRET_KEY = key
        Constants.KEY.SECRET_TOKEN = token
        val i = Intent(context, MyService::class.java)
        context.startService(i)
        context.bindService(i, createToken, Context.BIND_AUTO_CREATE);
    }

    fun loginWithKey(key: String, context: Context, loginWithKeyCallBack: LoginWithKeyCallBack) {
        this.loginWithKeyCallBack = loginWithKeyCallBack
        this.key = key
        val i = Intent(context, MyService::class.java)
        context.bindService(i, loginWithKey, Context.BIND_AUTO_CREATE);
    }

    fun loginWithToken(key: String, context: Context) {
        this.key = key
        val i = Intent(context, MyService::class.java)
        context.bindService(i, loginWithToken, Context.BIND_AUTO_CREATE);
    }

    private val createToken: ServiceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.getService(object : CreateTokenCallBack {
                override fun tokenCreate(token: String) {
                    userToken = token
                    createTokenCallback?.tokenCreate(token)
                }

                override fun failToCreate(token: String) {
                    createTokenCallback?.failToCreate(token)
                }
            })
            mServiceBound = true
        }

    }

    private val loginWithKey: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.loginWithKey(key, userToken, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    loginWithKeyCallBack?.onSuccess(msg)
                }

                override fun onFail(msg: String) {
                    loginWithKeyCallBack?.onFail(msg)
                }
            })
            mServiceBound = true
        }
    }

    private val loginWithToken: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.loginWithToken(key, userToken, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Log.e(">>>> loginWithToken", "onSuccess: " + msg)
                }

                override fun onFail(msg: String) {
                    Log.e(">>>> loginWithToken", "onFail: " + msg)
                }
            })
            mServiceBound = true
        }
    }
}