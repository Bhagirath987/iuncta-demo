package com.demo.mobile.app.ui.main.callback

interface CreateTokenCallBack {
    fun tokenCreate(token: String)
    fun failToCreate(token: String)
}