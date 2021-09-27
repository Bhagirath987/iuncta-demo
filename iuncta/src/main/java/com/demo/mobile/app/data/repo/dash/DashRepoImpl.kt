package com.demo.mobile.app.data.repo.dash

import com.demo.mobile.app.data.local.SharedPref
import com.demo.mobile.app.data.remote.api.DashApi

class DashRepoImpl(private val dashApi: DashApi, private val sharedPref: SharedPref) : DashRepo