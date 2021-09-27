package com.demo.mobile.app.di.module

import androidx.lifecycle.ViewModel
import com.demo.mobile.app.di.mapkey.ViewModelKey
import com.demo.mobile.app.ui.main.detail.DetailActivityVM
import com.demo.mobile.app.ui.main.profile.HomeActivityVM
import com.demo.mobile.app.ui.welcome.password.ForgotPasswordActivityVM
import com.demo.mobile.app.ui.welcome.section.register.RegisterActivityVM
import com.demo.mobile.app.ui.welcome.section.splash.SplashActivityVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityVM::class)
    abstract fun splashActivityVM(vm: SplashActivityVM): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityVM::class)
    abstract fun profileActivityVM(vm: HomeActivityVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordActivityVM::class)
    abstract fun forgotPasswordActivityVM(vm: ForgotPasswordActivityVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityVM::class)
    abstract fun detailActivityVM(vm: DetailActivityVM): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RegisterActivityVM::class)
    abstract fun registerActivityVM(vm: RegisterActivityVM): ViewModel

}