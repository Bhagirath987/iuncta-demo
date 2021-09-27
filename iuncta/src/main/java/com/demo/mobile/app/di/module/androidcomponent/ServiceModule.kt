package com.demo.mobile.app.di.module.androidcomponent

import com.demo.mobile.app.ui.main.MyService
import com.demo.mobile.app.util.services.FireBaseServices
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun fireBaseServices(): FireBaseServices


    @ContributesAndroidInjector
    abstract fun myService(): MyService
}