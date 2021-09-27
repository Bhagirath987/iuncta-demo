package com.iuncta.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.mobile.app.ui.main.profile.Iuncta
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var iuncta: Iuncta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStart.setOnClickListener {
            iuncta = Iuncta()
            iuncta!!.setSecret("ahVLpSF17k", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.ImFoVkxwU0YxN2si.Nu0gbkzHJVKBFyLSXVhXnErZzI3HzhaX5bSRds97AzA", this)
        }
    }


}