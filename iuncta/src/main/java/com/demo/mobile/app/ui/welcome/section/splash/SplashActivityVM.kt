package com.demo.mobile.app.ui.welcome.section.splash

import com.demo.mobile.app.di.base.viewmodel.BaseViewModel
import com.demo.mobile.app.util.event.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivityVM @Inject constructor() : BaseViewModel() {

    @JvmField
    val obrNext: SingleLiveEvent<Boolean> = SingleLiveEvent<Boolean>()

    fun nextScreen() {
        compositeDisposable.add(Observable.just(true)
            .delay(3300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { aBoolean -> obrNext.value = aBoolean })
    }
}