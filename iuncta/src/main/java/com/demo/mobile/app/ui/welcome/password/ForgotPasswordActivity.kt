package com.demo.mobile.app.ui.welcome.password

import android.content.Context
import android.content.Intent
import com.demo.mobile.app.R
import com.demo.mobile.app.databinding.ActivityForgotPasswordBinding
import com.demo.mobile.app.di.base.view.AppActivity

class ForgotPasswordActivity : AppActivity<ActivityForgotPasswordBinding, ForgotPasswordActivityVM>() {

    fun newIntent(context: Context): Intent {
        val intent = Intent(context, ForgotPasswordActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return intent
    }

    override fun getBindingActivity(): BindingActivity<ForgotPasswordActivityVM> {
        return BindingActivity(R.layout.activity_forgot_password, ForgotPasswordActivityVM::class.java)
    }

    override fun subscribeToEvents(vm: ForgotPasswordActivityVM) {
        vm.obrClick.observe(this, { view ->
            when (view.id) {

            }
        })
    }
}