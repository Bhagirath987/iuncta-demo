package com.demo.mobile.app.di.module

import com.demo.mobile.app.util.misc.RxBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ManagerModule {
    @JvmStatic
    @get:Provides
    @get:Singleton
    val bus: RxBus
        get() = RxBus()
}