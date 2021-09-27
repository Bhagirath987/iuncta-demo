package com.demo.mobile.app.ui.main.profile

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.demo.mobile.app.R
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.data.remote.helper.Status
import com.demo.mobile.app.databinding.ActivityProfileBinding
import com.demo.mobile.app.di.base.view.AppActivity
import com.demo.mobile.app.ui.welcome.section.register.RegisterActivity

import com.google.gson.Gson
import com.pixplicity.easyprefs.library.Prefs

class HomeActivity : AppActivity<ActivityProfileBinding, HomeActivityVM>() {

    fun newIntent(context: Context): Intent {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        return intent
    }

    override fun getBindingActivity(): BindingActivity<HomeActivityVM> {
        return BindingActivity(R.layout.activity_profile, HomeActivityVM::class.java)
    }

    override fun subscribeToEvents(vm: HomeActivityVM) {
        Prefs.putString(Constants.PrefsKeys.AUTHENTICATION, "")
        vm.obrClick.observe(this, { view ->
            when (view.id) {
                R.id.tvRegistration -> {
                    startNewActivity(RegisterActivity().newIntent(this, 1))
                }
                R.id.tvLogin -> {
                    startNewActivity(RegisterActivity().newIntent(this, 2))
                }
                R.id.tvLoginKey -> {
                    startNewActivity(RegisterActivity().newIntent(this, 3))
                }
                R.id.tvLoginToken -> {
                    startNewActivity(RegisterActivity().newIntent(this, 4))
                }

                R.id.tvPayment -> {
                    startNewActivity(RegisterActivity().newIntent(this, 5))
                }

            }
        })
        vm.obrAccessToken.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showProgressDialog(R.string.plz_wait)
                }
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    vm.success.value = it.message
                    Prefs.putString(Constants.PrefsKeys.AUTHENTICATION, Gson().toJson(it.data.data.accessToken))
                }
                Status.WARN -> {
                    dismissProgressDialog()
                    vm.warn.value = it.message

                }
                Status.ERROR -> {
                    dismissProgressDialog()
                    vm.error.value = it.message

                }
            }
        })
        vm.createprofileObr.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showProgressDialog(R.string.plz_wait)
                }
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    Prefs.putString(Constants.PrefsKeys.AUTHENTICATION, Gson().toJson(it.data.data.accessToken))
                }
                Status.WARN -> {
                    dismissProgressDialog()
                    vm.warn.value = it.message
                }
                Status.ERROR -> {
                    dismissProgressDialog()
                    vm.error.value = it.message
                }
            }
        })
        accessTokenApi()
    }

    public fun accessTokenApi() {
        val rqMap: HashMap<String, String> = HashMap()
        rqMap["secretkey"] = Constants.KEY.SECRET_KEY
        rqMap["secrettoken"] = Constants.KEY.SECRET_TOKEN
        viewModel.createToken(Request.CreateToken(Constants.KEY.SECRET_KEY, Constants.KEY.SECRET_TOKEN))


        viewModel.createprofileObr.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    Prefs.putString(Constants.PrefsKeys.AUTHENTICATION, Gson().toJson(it.data.data.accessToken))
                }
                Status.WARN -> {
                    dismissProgressDialog()
                    viewModel.warn.value = it.message
                }
                Status.ERROR -> {
                    dismissProgressDialog()
                    viewModel.error.value = it.message
                }
            }
        })
    }


}