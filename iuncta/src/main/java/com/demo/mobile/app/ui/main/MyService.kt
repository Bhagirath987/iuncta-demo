package com.demo.mobile.app.ui.main

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.demo.mobile.app.data.beans.AccessTokenResponse
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.data.remote.helper.ApiCallback
import com.demo.mobile.app.data.repo.welcome.WelcomeRepo
import com.demo.mobile.app.ui.main.profile.Request
import dagger.android.DaggerService
import retrofit2.Response
import javax.inject.Inject


public class MyService : DaggerService() {

    @JvmField
    @Inject
    var welcomeRepo: WelcomeRepo? = null

    private val binder: IBinder = MyBinder()
    override fun onBind(intent: Intent): IBinder? {
        Log.e(">>>>", "onBind: ")
        return binder
    }

    override fun onRebind(intent: Intent) {
        super.onRebind(intent)
        Log.e(">>>>", "In OnReBind")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(">>>>", "Service Destroyed")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e(">>>>", "In onStartCommend, thread id: ")
        welcomeRepo?.requestUserVerify(Request.CreateToken(Constants.KEY.SECRET_KEY, Constants.KEY.SECRET_TOKEN), object : ApiCallback<Response<AccessTokenResponse>>() {
            override fun onSuccess(response: Response<AccessTokenResponse>) {
                Log.e(">>>>", "onSuccess: ")
                //  createprofileObr.value = Resource.success(response.body(), response.body()?.message ?: "")
            }

            override fun onFailed(message: String) {
                Log.e(">>>>", "onFailed: ")
                // createprofileObr.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                Log.e(">>>>", "onErrorThrow: ")
                // createprofileObr.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                Log.e(">>>>", "onLoading: ")
                //createprofileObr.value = Resource.loading(null, "")
            }
        })

        return START_STICKY
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.e(">>>>", "In onUnbind")
        return super.onUnbind(intent)
    }

    inner class MyBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

}