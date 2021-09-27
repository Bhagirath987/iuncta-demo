package com.demo.mobile.app.di.base.view

import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.demo.mobile.app.data.beans.connection.ConnectionBean
import com.demo.mobile.app.data.local.SharedPref
import com.demo.mobile.app.di.base.viewmodel.BaseViewModel
import com.demo.mobile.app.util.event.SingleMessageEvent.MessageObserver
import com.demo.mobile.app.util.misc.ConnectionHandler
import com.demo.mobile.app.util.view.MessageUtils
import javax.inject.Inject

abstract class AppActivity<B : ViewDataBinding?, V : BaseViewModel?> : BaseActivity<B, V>() {

    @JvmField
    protected val TAG = this.javaClass.simpleName

    @set:Inject
    open var sharedPref: SharedPref? = null

    @set:Inject
    open var connectionHandler: ConnectionHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeBaseEvents()
    }

    private fun subscribeBaseEvents() {
        viewModel!!.normal.observe(this, object : MessageObserver {
            override fun onMessageReceived(msgResId: Int) {
                MessageUtils.normal(this@AppActivity, getString(msgResId))
            }

            override fun onMessageReceived(msg: String) {
                if (!TextUtils.isEmpty(msg)) MessageUtils.normal(this@AppActivity, msg)
            }
        })
        viewModel!!.success.observe(this, object : MessageObserver {
            override fun onMessageReceived(msgResId: Int) {
                MessageUtils.success(this@AppActivity, getString(msgResId))
            }

            override fun onMessageReceived(msg: String) {
                if (!TextUtils.isEmpty(msg)) MessageUtils.success(this@AppActivity, msg)
            }
        })
        viewModel!!.error.observe(this, object : MessageObserver {
            override fun onMessageReceived(msgResId: Int) {
                MessageUtils.error(this@AppActivity, getString(msgResId))
            }

            override fun onMessageReceived(msg: String) {
                if (!TextUtils.isEmpty(msg)) MessageUtils.error(this@AppActivity, msg)
            }
        })
        viewModel!!.warn.observe(this, object : MessageObserver {
            override fun onMessageReceived(msgResId: Int) {
                MessageUtils.warning(this@AppActivity, getString(msgResId))
            }

            override fun onMessageReceived(msg: String) {
                if (!TextUtils.isEmpty(msg)) MessageUtils.warning(this@AppActivity, msg)
            }
        })
        viewModel!!.info.observe(this, object : MessageObserver {
            override fun onMessageReceived(msgResId: Int) {
                MessageUtils.info(this@AppActivity, getString(msgResId))
            }

            override fun onMessageReceived(msg: String) {
                if (!TextUtils.isEmpty(msg)) MessageUtils.info(this@AppActivity, msg)
            }
        })
        connectionHandler!!.observe(this, Observer observe@{ connectionBean: ConnectionBean? ->
            if (connectionBean == null) return@observe
            if (connectionBean.type == ConnectionBean.State.NONE) {
                onInternetRefresh(false)
            } else {
                onInternetRefresh(true)
            }
        })
    }

    protected open fun onInternetRefresh(isConnected: Boolean) {}
    override fun onBackPressed() {
        super.onBackPressed(true)
    }
}