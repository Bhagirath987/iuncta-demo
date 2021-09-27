package com.demo.mobile.app.data.remote.intersepter

import com.demo.mobile.app.di.base.MyApplication
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val mainResponse = chain.proceed(chain.request())
        if (mainResponse.code == 401) {
            if (MyApplication.instance?.isAuthError() == true) {
              //  MyApplication.instance?.restartApp()
            } else {
           //     MyApplication.instance?.refreshToken()
            }
        }
        return mainResponse
    }
}