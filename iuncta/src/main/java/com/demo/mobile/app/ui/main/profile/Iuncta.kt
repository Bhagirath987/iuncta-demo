package com.demo.mobile.app.ui.main.profile

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.data.beans.GetRequestData
import com.demo.mobile.app.data.beans.RegisterResponse
import com.demo.mobile.app.ui.main.MyService
import com.demo.mobile.app.ui.main.callback.CreateTokenCallBack
import com.demo.mobile.app.ui.main.callback.LoginWithKeyCallBack


class Iuncta(context: Context) {
    var mBoundService: MyService? = null
    var userToken: String = ""
    var key: String = ""
    var userTokenForGettingData: String = ""
    var userName: String = ""
    var mServiceBound: Boolean = false
    var createTokenCallback: CreateTokenCallBack? = null
    var loginWithKeyCallBack: LoginWithKeyCallBack? = null
    var loginWithTokenCallback: LoginWithKeyCallBack? = null
    var simpleLoginRequest: LoginWithKeyCallBack? = null
    var requestDataCallback: LoginWithKeyCallBack? = null
    var registerRequest: LoginWithKeyCallBack? = null
    var paymentCallBack: LoginWithKeyCallBack? = null
    var context: Context? = context
    var userIntent: Intent? = null
    var bindSimpleLogin = false
    var bindRegistration = false
    var bindPayment = false
    var bindLoginWithKeyValue = false
    var loginWithTokenValue = false
    var getRequestDataValue = false
    var bindcreateToken = false
    var paymentData: PaymentRequestData = PaymentRequestData()

    init {
        userIntent = Intent(context, MyService::class.java)
        context.startService(userIntent)
    }



    fun setSecret(
        key: String,
        token: String,
        context: Context,
        createTokenCallback: CreateTokenCallBack
    ) {
        this.createTokenCallback = createTokenCallback
        Constants.KEY.SECRET_KEY = key
        Constants.KEY.SECRET_TOKEN = token

        if (bindcreateToken) {
            context.unbindService(createToken)
            bindcreateToken = false
        }
        bindcreateToken = context.bindService(userIntent, createToken, Context.BIND_AUTO_CREATE);
    }

    fun loginWithKey(key: String, context: Context, loginWithKeyCallBack: LoginWithKeyCallBack) {
        this.loginWithKeyCallBack = loginWithKeyCallBack
        this.key = key
        val i = Intent(context, MyService::class.java)

        if (bindLoginWithKeyValue) {
            context.unbindService(loginWithKey)
            bindLoginWithKeyValue = false
        }


        bindLoginWithKeyValue = context.bindService(i, loginWithKey, Context.BIND_AUTO_CREATE);
    }

    fun loginWithToken(key: String, context: Context, loginWithKeyCallBack: LoginWithKeyCallBack) {
        this.loginWithTokenCallback = loginWithKeyCallBack
        this.key = key
        val i = Intent(context, MyService::class.java)

        if (loginWithTokenValue) {
            context.unbindService(loginWithToken)
            loginWithTokenValue = false
        }

        loginWithTokenValue = context.bindService(i, loginWithToken, Context.BIND_AUTO_CREATE);
    }

    fun simpleLogin(userName: String, context: Context, simpleLoginRequest: LoginWithKeyCallBack) {
        this.simpleLoginRequest = simpleLoginRequest
        this.userName = userName
        if (bindSimpleLogin) {
            context.unbindService(simpleLogin)
            bindSimpleLogin = false
        }
        bindSimpleLogin = context.bindService(userIntent, simpleLogin, Context.BIND_AUTO_CREATE)
    }

    fun sendRegistrationRequest(userName: String, context: Context, registerRequest: LoginWithKeyCallBack) {
        this.registerRequest = registerRequest
        this.userName = userName
        if (bindRegistration) {
            context.unbindService(registrationRequest)
            bindRegistration = false
        }
        bindRegistration = context.bindService(userIntent, registrationRequest, Context.BIND_AUTO_CREATE)
    }

    fun getRequestData(userTokenForGettingData: String, context: Context, requestDataCallback: LoginWithKeyCallBack) {
        this.requestDataCallback = requestDataCallback
        this.userTokenForGettingData = userTokenForGettingData
        if (getRequestDataValue) {
            context.unbindService(getRequestData)
            getRequestDataValue = false
        }
        getRequestDataValue = context.bindService(userIntent, getRequestData, Context.BIND_AUTO_CREATE)
    }

    fun sendPaymentRequest(userName: String, paymentData: PaymentRequestData, context: Context, paymentCallBack: LoginWithKeyCallBack) {
        this.paymentCallBack = paymentCallBack
        this.paymentData = paymentData
        this.userName = userName
        if (bindPayment) {
            context.unbindService(paymentRequest)
            bindPayment = false
        }
        bindPayment = context.bindService(userIntent, paymentRequest, Context.BIND_AUTO_CREATE)
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
                    loginWithTokenCallback?.onSuccess(msg)
                }

                override fun onFail(msg: String) {
                    Log.e(">>>> loginWithToken", "onFail: " + msg)
                    loginWithTokenCallback?.onFail(msg)
                }
            })
            mServiceBound = true
        }
    }

    private var simpleLogin: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.simpleLogin(userName, userToken, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Log.e(">>>> loginWithToken", "onSuccess: " + msg)
                    simpleLoginRequest?.onSuccess(msg)
                }

                override fun onFail(msg: String) {
                    Log.e(">>>> loginWithToken", "onFail: " + msg)
                    simpleLoginRequest?.onFail(msg)

                }
            })
            mServiceBound = true
        }
    }

    private var getRequestData: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.getRequestData(userTokenForGettingData, userToken, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    requestDataCallback?.onSuccess(msg)
                }

                override fun onFail(msg: String) {
                    requestDataCallback?.onFail(msg)
                }

                override fun getData(data: GetRequestData) {
                    super.getData(data)
                    requestDataCallback?.getData(data)
                }
            })
            mServiceBound = true
        }
    }

    private var registrationRequest: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.registerRequest(userName, userToken, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Log.e(">>>> loginWithToken", "onSuccess: " + msg)
                    registerRequest?.onSuccess(msg)
                }

                override fun onFail(msg: String) {
                    Log.e(">>>> loginWithToken", "onFail: " + msg)
                    registerRequest?.onFail(msg)

                }
            })
            mServiceBound = true
        }
    }

    private var paymentRequest: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder: MyService.MyBinder = service as MyService.MyBinder
            mBoundService = myBinder.sendPayment(userToken, paymentData, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Log.e(">>>> loginWithToken", "onSuccess: " + msg)
                    paymentCallBack?.onSuccess(msg)
                }

                override fun onFail(msg: String) {
                    Log.e(">>>> loginWithToken", "onFail: " + msg)
                    paymentCallBack?.onFail(msg)
                }

                override fun getPaymentResponse(data: RegisterResponse) {
                    super.getPaymentResponse(data)
                    Log.e(">>>>", "getPaymentResponse: " + data)
                }
            })
            mServiceBound = true
        }
    }
}