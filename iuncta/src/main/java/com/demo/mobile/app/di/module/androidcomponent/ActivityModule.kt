package com.demo.mobile.app.di.module.androidcomponent

 import com.demo.mobile.app.ui.main.detail.DetailActivity
 import com.demo.mobile.app.ui.main.profile.HomeActivity
 import com.demo.mobile.app.ui.welcome.password.ForgotPasswordActivity
 import com.demo.mobile.app.ui.welcome.section.register.RegisterActivity
 import com.demo.mobile.app.ui.welcome.section.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun profileActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun forgotPasswordActivity(): ForgotPasswordActivity


    @ContributesAndroidInjector
    abstract fun detailActivity(): DetailActivity


    @ContributesAndroidInjector
    abstract fun registerActivity(): RegisterActivity




}