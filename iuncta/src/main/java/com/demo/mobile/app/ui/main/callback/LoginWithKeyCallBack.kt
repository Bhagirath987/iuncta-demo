package com.demo.mobile.app.ui.main.callback

import com.demo.mobile.app.data.beans.GetRequestData

interface LoginWithKeyCallBack {
    fun onSuccess(msg: String)
    fun onFail(msg: String)
    fun getData(data: GetRequestData) {}
}