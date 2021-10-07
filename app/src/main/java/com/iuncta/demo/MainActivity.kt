package com.iuncta.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.mobile.app.ui.main.callback.CreateTokenCallBack
import com.demo.mobile.app.ui.main.callback.LoginWithKeyCallBack
import com.demo.mobile.app.ui.main.profile.Iuncta
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var iuncta: Iuncta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initIuncta()
        btnStart.setOnClickListener {
            initIuncta()
        }

        btnLoginWithKey.setOnClickListener {
            iuncta?.loginWithKey(edKey.text.toString(), this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
            })
        }

        btnToken.setOnClickListener {
            iuncta?.loginWithToken(edToken.text.toString(), this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
            })
        }

        btnLogin.setOnClickListener {
            iuncta?.simpleLogin(edUserName.text.toString(), this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
            })
        }
        btnCheckData.setOnClickListener {
            iuncta?.simpleLogin(edUserName.text.toString(), this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    fun initIuncta() {
        iuncta = Iuncta(this)
        iuncta!!.setSecret(
            "ahVLpSF17k",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.ImFoVkxwU0YxN2si.Nu0gbkzHJVKBFyLSXVhXnErZzI3HzhaX5bSRds97AzA",
            this, object : CreateTokenCallBack {
                override fun tokenCreate(token: String) {
                    Toast.makeText(this@MainActivity, "Iuncta Init", Toast.LENGTH_LONG).show()
                }

                override fun failToCreate(token: String) {
                    Toast.makeText(this@MainActivity, token, Toast.LENGTH_LONG).show()
                }
            })
    }
}