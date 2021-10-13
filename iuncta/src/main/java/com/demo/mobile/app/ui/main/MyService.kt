package com.demo.mobile.app.ui.main

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.demo.mobile.app.data.beans.*
import com.demo.mobile.app.data.remote.helper.ApiCallback
import com.demo.mobile.app.data.repo.welcome.WelcomeRepo
import com.demo.mobile.app.ui.main.callback.CreateTokenCallBack
import com.demo.mobile.app.ui.main.callback.LoginWithKeyCallBack
import com.demo.mobile.app.ui.main.profile.Request
import dagger.android.DaggerService
import retrofit2.Response
import java.util.*
import javax.inject.Inject


public class MyService : DaggerService() {
    @JvmField
    @Inject
    var welcomeRepo: WelcomeRepo? = null
    var callbacks: CreateTokenCallBack? = null
    var loginWithKeyCallBack: LoginWithKeyCallBack? = null

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
        return START_STICKY
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.e(">>>>", "In onUnbind")
        return super.onUnbind(intent)
    }

    inner class MyBinder : Binder() {
        fun getService(callback: CreateTokenCallBack): MyService {
            callbacks = callback
            welcomeRepo?.requestUserVerify(
                Request.CreateToken(
                    Constants.KEY.SECRET_KEY,
                    Constants.KEY.SECRET_TOKEN
                ), object : ApiCallback<Response<AccessTokenResponse>>() {
                    override fun onSuccess(response: Response<AccessTokenResponse>) {
                        response.body()?.data?.accessToken?.let { callbacks?.tokenCreate(it) }
                    }

                    override fun onFailed(message: String) {
                        callbacks?.failToCreate("" + message)
                    }

                    override fun onErrorThrow(exception: Exception) {
                        callbacks?.failToCreate("" + exception.message)
                    }

                    override fun onLoading() {

                    }
                })
            return this@MyService
        }

        fun loginWithKey(
            key: String,
            userToken: String,
            loginWithKeyCallBack: LoginWithKeyCallBack
        ): MyService {
            val rqMap: MutableMap<String, String> = HashMap()
            rqMap["token"] = key
            rqMap["type"] = "0"
            rqMap["secretkey"] = Constants.KEY.SECRET_KEY
            welcomeRepo?.verifyToken(
                "Bearer $userToken",
                rqMap,
                object : ApiCallback<Response<VerifyTokenResponse>>() {
                    override fun onSuccess(response: Response<VerifyTokenResponse>) {
                        loginWithKeyCallBack.onSuccess(response.body()?.message ?: "Success")
                    }

                    override fun onFailed(message: String) {
                        loginWithKeyCallBack.onFail(message)
                    }

                    override fun onErrorThrow(exception: Exception) {
                        loginWithKeyCallBack.onFail("" + exception.message)
                    }

                    override fun onLoading() {

                    }
                })
            return this@MyService
        }


        fun loginWithToken(
            key: String,
            userToken: String,
            loginWithKeyCallBack: LoginWithKeyCallBack
        ): MyService {
            val rqMap: MutableMap<String, String> = HashMap()
            rqMap["token"] = key
            rqMap["type"] = "1"
            rqMap["secretkey"] = Constants.KEY.SECRET_KEY
            welcomeRepo?.verifyToken(
                "Bearer $userToken",
                rqMap,
                object : ApiCallback<Response<VerifyTokenResponse>>() {
                    override fun onSuccess(response: Response<VerifyTokenResponse>) {
                        loginWithKeyCallBack.onSuccess(response.body()?.data.toString())
                    }

                    override fun onFailed(message: String) {
                        loginWithKeyCallBack.onFail(message)
                    }

                    override fun onErrorThrow(exception: Exception) {
                        loginWithKeyCallBack.onFail("" + exception.message)
                    }

                    override fun onLoading() {

                    }
                })
            return this@MyService
        }


        fun simpleLogin(
            userName: String,
            userToken: String,
            simpleLoginCallback: LoginWithKeyCallBack
        ): MyService {
            val rqMap: MutableMap<String, String> = HashMap()
            rqMap["username"] = userName
            rqMap["secretkey"] = Constants.KEY.SECRET_KEY
            rqMap["login_type"] = "2"
            rqMap["request_json"] = "full_name,gender,birthdate,phone_number,email,website,profile_picture,address,country"

            welcomeRepo?.requestUserVerify("Bearer $userToken", rqMap, object : ApiCallback<Response<RegisterResponse>>() {
                override fun onSuccess(response: Response<RegisterResponse>) {
                    simpleLoginCallback.onSuccess("" + response.body()?.data?.sendRequest)
                }

                override fun onFailed(message: String) {
                    simpleLoginCallback.onFail(message)
                }

                override fun onErrorThrow(exception: Exception) {
                    simpleLoginCallback.onFail("" + exception.message)
                }

                override fun onLoading() {

                }
            })
            return this@MyService
        }

        fun registerRequest(
            userName: String,
            userToken: String,
            simpleLoginCallback: LoginWithKeyCallBack
        ): MyService {
            val rqMap: MutableMap<String, String> = HashMap()
            rqMap["username"] = userName
            rqMap["secretkey"] = Constants.KEY.SECRET_KEY
            rqMap["login_type"] = "1"
            rqMap["request_json"] = "full_name,gender,birthdate,phone_number,email,website,profile_picture,address,country"
            welcomeRepo?.requestUserVerify("Bearer $userToken", rqMap, object : ApiCallback<Response<RegisterResponse>>() {
                override fun onSuccess(response: Response<RegisterResponse>) {
                    simpleLoginCallback.onSuccess("" + response.body()?.data?.sendRequest)
                }

                override fun onFailed(message: String) {
                    simpleLoginCallback.onFail(message)
                }

                override fun onErrorThrow(exception: Exception) {
                    simpleLoginCallback.onFail("" + exception.message)
                }

                override fun onLoading() {

                }
            })
            return this@MyService
        }


        fun getRequestData(
            send_token: String,
            userToken: String,
            simpleLoginCallback: LoginWithKeyCallBack
        ): MyService {
            val rqMap: MutableMap<String, String> = HashMap()
            rqMap["send_request"] = send_token
            welcomeRepo?.getRequest("Bearer $userToken", rqMap, object : ApiCallback<Response<GetRequestResponse>>() {
                override fun onSuccess(response: Response<GetRequestResponse>) {
                    simpleLoginCallback.onSuccess("${response.body()?.message}")
                    response.body()?.data?.let { simpleLoginCallback.getData(it) }
                }

                override fun onFailed(message: String) {
                    simpleLoginCallback.onFail(message)
                }

                override fun onErrorThrow(exception: Exception) {
                    simpleLoginCallback.onFail("" + exception.message)
                }

                override fun onLoading() {
                }
            })
            return this@MyService
        }
    }
}