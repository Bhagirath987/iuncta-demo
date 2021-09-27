package com.demo.mobile.app.di.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.multidex.MultiDex
import com.google.gson.Gson
import com.pixplicity.easyprefs.library.Prefs
import com.demo.mobile.app.data.beans.Authentication
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.data.beans.UserBean
import com.demo.mobile.app.data.beans.base.ApiResponse
import com.demo.mobile.app.data.remote.helper.ApiCallback
import com.demo.mobile.app.data.repo.welcome.WelcomeRepo
import com.demo.mobile.app.di.component.DaggerAppComponent
import com.demo.mobile.app.ui.welcome.section.splash.SplashActivity

import com.demo.mobile.app.util.loggerE
import com.demo.mobile.app.util.showToast
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.exceptions.ProtocolViolationException
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import timber.log.Timber.DebugTree


class MyApplication : DaggerApplication() {

    private var isAuthErrorForRefreshToken: Boolean = false
    private lateinit var welcomeRepo: WelcomeRepo
    private var context: Context? = null

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()

        instance = this
        MultiDex.install(this)
        Timber.plant(DebugTree())
        attachErrorHandler()

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    fun restartApp() {
        CoroutineScope(Dispatchers.Main).launch {
            Prefs.clear()

        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    private fun attachErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable ->
            if (throwable is OnErrorNotImplementedException || throwable is ProtocolViolationException) {
                loggerE("attachErrorHandler")
            }
        }
    }

    fun setCurrentActivity(context: Context) {
        this.context = context
    }

    fun getCurrentActivity(): Context? {
        return context
    }

    fun getUserAuthentication(): Authentication? {
        return Gson().fromJson(Prefs.getString(Constants.PrefsKeys.AUTHENTICATION, ""), Authentication::class.java)
    }

    fun getToken(): String {
        return "Bearer ${getUserAuthentication()?.accessToken}"
    }

    fun setAuthRepo(welcomeRepo: WelcomeRepo) {
        this.welcomeRepo = welcomeRepo
    }

    fun refreshToken() {
        welcomeRepo.refreshTokenNew(object : ApiCallback<Response<ApiResponse<Authentication>>>() {

            override fun onSuccess(response: Response<ApiResponse<Authentication>>) {
                Log.e("success", "onSuccess: ")
                setAuthError(false)
                Prefs.putString(Constants.PrefsKeys.AUTHENTICATION, Gson().toJson(response.body()?.data, Authentication::class.java))
            }

            override fun onFailed(message: String) {
                Log.e("failed", "onFailed: ")
                showToast(message)
                setAuthError(true)
            }

            override fun onErrorThrow(exception: Exception) {
                Log.e("error", "onErrorThrow: ")
                showToast(exception.message.toString())
                setAuthError(true)
            }

            override fun onLoading() {
                Log.e("load", "onLoading: ")
            }
        })
    }

    fun isAuthError(): Boolean {
        return this.isAuthErrorForRefreshToken
    }

    fun setAuthError(isAuthErrorForRefresh: Boolean) {
        this.isAuthErrorForRefreshToken = isAuthErrorForRefresh
    }

    fun getUserData(): UserBean {
        return Gson().fromJson(Prefs.getString(Constants.PrefsKeys.USER_DATA, ""), UserBean::class.java)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        var instance: MyApplication? = null
            private set
    }
}