package com.iuncta.demo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.data.beans.GetRequestData
import com.demo.mobile.app.data.beans.RegisterResponse
import com.demo.mobile.app.ui.main.callback.CreateTokenCallBack
import com.demo.mobile.app.ui.main.callback.LoginWithKeyCallBack
import com.demo.mobile.app.ui.main.profile.Iuncta
import com.demo.mobile.app.ui.main.profile.PaymentRequestData
import com.demo.mobile.app.ui.main.profile.PurchaseData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var iuncta: Iuncta? = null
    var currentRequestToken: String = ""

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
                    currentRequestToken = msg
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
            })
        }

        btnSendRegistrationRequest.setOnClickListener {
            iuncta?.sendRegistrationRequest(edRegistration.text.toString(), this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                    currentRequestToken = msg
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
            })
        }

        btnCheckData.setOnClickListener {
            iuncta?.getRequestData(currentRequestToken, this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
            })
        }

        btnCheckRegistrationData.setOnClickListener {
            iuncta?.getRequestData(currentRequestToken, this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun getData(data: GetRequestData) {
                    super.getData(data)
                    Log.e("MainActivity", "getData: " + data.fullName)

                }
            })
        }

        btnCheckPaymentData.setOnClickListener {
            btnCheckRegistrationData.performClick()
        }

        btnSendPaymentRequest.setOnClickListener {
            var purchaseData = ArrayList<PurchaseData>()
            purchaseData.add(
                PurchaseData(
                    amount = "49000",
                    description ="Awesome",
                    discount = "10",
                    productId = "101441",
                    finalAmount = "48000",
                    title = "Play Station 5"
                )
            )
            val data = PaymentRequestData(
                loginType = "5",
                orderId = "1234578",
                purchaseData,
                "full_name,phone_number,profile_picture,email",
                Constants.KEY.SECRET_KEY, Constants.KEY.SECRET_TOKEN,
                "123456",
                "1234568",
                totalAmount = "56445",
                username = edPayment.text.toString())

            iuncta?.sendPaymentRequest(edPayment.text.toString(), data, this, object : LoginWithKeyCallBack {
                override fun onSuccess(msg: String) {
                    currentRequestToken = msg
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }
                override fun onFail(msg: String) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                }

                override fun getPaymentResponse(data: RegisterResponse) {
                    super.getPaymentResponse(data)
                    Log.e("MainActivity", "getData: " + data.data)
                }
            })
        }
    }

    private fun initIuncta() {
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