package com.demo.mobile.app.ui.welcome.section.splash

import android.content.Intent
import android.view.View
import com.demo.mobile.app.R
import com.demo.mobile.app.databinding.ActivitySplashBinding
import com.demo.mobile.app.di.base.MyApplication
import com.demo.mobile.app.di.base.view.AppActivity
import com.demo.mobile.app.ui.main.profile.HomeActivity

class SplashActivity : AppActivity<ActivitySplashBinding, SplashActivityVM>() {

    override fun getBindingActivity(): BindingActivity<SplashActivityVM> {
        return BindingActivity(R.layout.activity_splash, SplashActivityVM::class.java)
    }

    override fun subscribeToEvents(vm: SplashActivityVM) {
        vm.obrClick.observe(this, { v: View ->
            when (v.id) {
            }
        })
        vm.obrNext.observe(this, {
            val intent: Intent
            intent = HomeActivity().newIntent(this@SplashActivity)
            startNewActivity(intent, true)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.nextScreen()
    }

    companion object {
        fun newIntent(activity: MyApplication?): Intent {
            val intent = Intent(activity, SplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return intent
        }
    }
}