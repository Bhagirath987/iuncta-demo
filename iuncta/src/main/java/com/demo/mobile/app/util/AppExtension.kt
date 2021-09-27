package com.demo.mobile.app.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.demo.mobile.app.data.beans.UserBean
import com.demo.mobile.app.di.base.MyApplication

private var toast: Toast? = null

//Get userData
fun Activity.getUserData(): UserBean? {
    return MyApplication.instance?.getUserData()
}

fun Fragment.getUserData(): UserBean? {
    return MyApplication.instance?.getUserData()
}

//Log for activity
fun Activity.loggerD(msg: String) {
    Log.d(this.localClassName, msg)
}

fun Activity.loggerE(msg: String) {
    Log.e(this.localClassName, msg)
}

fun Activity.loggerI(msg: String) {
    Log.i(this.localClassName, msg)
}

fun Activity.loggerV(msg: String) {
    Log.v(this.localClassName, msg)
}

fun Activity.loggerW(msg: String) {
    Log.w(this.localClassName, msg)
}

//Log for fragment
fun Fragment.loggerD(msg: String) {
    Log.d(this.javaClass.simpleName, msg)
}

fun Fragment.loggerE(msg: String) {
    Log.e(this.javaClass.simpleName, msg)
}

fun Fragment.loggerI(msg: String) {
    Log.i(this.javaClass.simpleName, msg)
}

fun Fragment.loggerV(msg: String) {
    Log.v(this.javaClass.simpleName, msg)
}

fun Fragment.loggerW(msg: String) {
    Log.w(this.javaClass.simpleName, msg)
}

//MyApplication class
fun Context.loggerE(msg: String) {
    Log.e(this.javaClass.simpleName, msg)
}

fun Context.showToast(message: String) {
    showMessage(this, message)
}

fun Activity.showToast(message: String) {
    showMessage(this, message)
}

fun Fragment.showToast(message: String) {
    showMessage(context ?: return, message)
}

private fun showMessage(context: Context, message: String) {
    if (toast != null) {
        toast?.cancel()
    }
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast?.show()
}