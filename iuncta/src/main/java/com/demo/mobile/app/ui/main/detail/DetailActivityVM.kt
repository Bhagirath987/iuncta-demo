package com.demo.mobile.app.ui.main.detail

import com.demo.mobile.app.data.remote.helper.NetworkErrorHandler
import com.demo.mobile.app.data.repo.welcome.WelcomeRepo
import com.demo.mobile.app.di.base.viewmodel.BaseViewModel
import javax.inject.Inject

class DetailActivityVM @Inject constructor(
    private val welcomeRepo: WelcomeRepo,
    private val networkErrorHandler: NetworkErrorHandler
) : BaseViewModel()