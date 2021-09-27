package com.demo.mobile.app.di.module

import android.content.Context
import android.content.SharedPreferences
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.data.local.SharedPref
import com.demo.mobile.app.data.local.SharedPrefImpl
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Module
object LocalModule {
    @Singleton
    @Provides
    fun sharedPreferences(daggerApplication: DaggerApplication): SharedPreferences {
        return daggerApplication.getSharedPreferences(
            Constants.appId,
            Context.MODE_PRIVATE
        )
    }

    @Singleton
    @Provides
    fun sharedPref(sharedPreferences: SharedPreferences): SharedPref {
        return SharedPrefImpl(sharedPreferences)
    }
}