package com.demo.mobile.app.di.module

import com.demo.mobile.app.data.local.SharedPref
import com.demo.mobile.app.data.remote.api.DashApi
import com.demo.mobile.app.data.remote.api.WelcomeApi
import com.demo.mobile.app.data.repo.dash.DashRepo
import com.demo.mobile.app.data.repo.dash.DashRepoImpl
import com.demo.mobile.app.data.repo.welcome.WelcomeRepo
import com.demo.mobile.app.data.repo.welcome.WelcomeRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun welcomeRepo(welcomeApi: WelcomeApi, sharedPref: SharedPref): WelcomeRepo {
        return WelcomeRepoImpl(welcomeApi, sharedPref)
    }

    @Singleton
    @Provides
    fun dashRepo(dashApi: DashApi, sharedPref: SharedPref): DashRepo {
        return DashRepoImpl(dashApi, sharedPref)
    }
}