package com.demo.mobile.app.ui.welcome.section.register

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.demo.mobile.app.R
import com.demo.mobile.app.data.beans.Constants
import com.demo.mobile.app.data.remote.helper.Status
import com.demo.mobile.app.databinding.ActivityRegisterBinding
import com.demo.mobile.app.di.base.view.AppActivity
import com.demo.mobile.app.ui.main.detail.DetailActivity
import com.demo.mobile.app.ui.main.profile.PaymentRequestData
import com.demo.mobile.app.ui.main.profile.PurchaseData
import com.demo.mobile.app.util.view.InputUtils.isEmpty
import com.pixplicity.easyprefs.library.Prefs
import java.util.*
import kotlin.collections.ArrayList

class RegisterActivity : AppActivity<ActivityRegisterBinding, RegisterActivityVM>() {
    private var type: Int = 0
    var sendRequest = ""

    fun newIntent(context: Context, type: Int): Intent {
        val intent = Intent(context, RegisterActivity::class.java)
        intent.putExtra("type", type)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        return intent
    }

    override fun getBindingActivity(): BindingActivity<RegisterActivityVM> {
        return BindingActivity(R.layout.activity_register, RegisterActivityVM::class.java)
    }

    override fun subscribeToEvents(vm: RegisterActivityVM) {

        vm.obrClick.observe(this, {
            when (it.id) {
                R.id.tvBack -> {
                    onBackPressed()
                }
                R.id.imgSubmit -> {
                    if (type == 1 && binding.edtUsername.text.isEmpty()) {
                        vm.warn.value = resources.getString(R.string.please_enter_username)
                    } else if (type == 2 && binding.edtUsername.text.isEmpty()) {
                        vm.warn.value = resources.getString(R.string.please_enter_username)
                    } else if (type == 3 && binding.edtUsername.text.isEmpty()) {
                        vm.warn.value = resources.getString(R.string.please_enter_key)
                    } else if (type == 4 && binding.edtUsername.text.isEmpty()) {
                        vm.warn.value = resources.getString(R.string.please_enter_token)
                    } else {
                        if (type == 1 || type == 2) {
                            registerApi()
                        } else {
                            if (type == 3) { //key
                                verifyTokenApi(0)
                            } else {
                                verifyTokenApi(1) //token
                            }
                        }
                    }
                }
                R.id.imgPaymentSubmit -> {
                    callPayment()
                }
            }
        })




//sticky intent



        type = intent.getIntExtra("type", 0)
        if (type == 1 || type == 2) {
            binding.edtUsername.hint = resources.getString(R.string.enter_username)
        } else if (type == 3) {
            binding.edtUsername.hint = resources.getString(R.string.enter_key)
        } else if (type == 4) {
            binding.edtUsername.hint = resources.getString(R.string.enter_token)
        } else if (type == 5) {
            binding.crdPaymentHolder.visibility = View.VISIBLE
            binding.cnsCommonHolder.visibility = View.GONE
        }







        vm.obrRegister.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showProgressDialog(R.string.plz_wait)
                }
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    vm.success.value = it.message
                    //Prefs.putString(Constants.PrefsKeys.SEND_REQUEST, it.data.data.accessToken)
                    sendRequest = it.data.data.sendRequest
                    getRequestApi(sendRequest)
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
        vm.obrSendPaymentData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showProgressDialog(R.string.plz_wait)
                }
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    vm.success.value = it.message
                    Log.e(">>>>", "subscribeToEvents: " + it.data)
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
        vm.obrGetRequest.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    //   showProgressDialog(R.string.plz_wait)
                }
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    vm.success.value = it.message
                    startNewActivity(DetailActivity().newIntent(this, it.data.data, type))
                }
                Status.WARN -> {
                    dismissProgressDialog()
                    vm.warn.value = it.message
                }
                Status.ERROR -> {
                    dismissProgressDialog()
                    if (it.message != "Your request is pending.") {
                        vm.error.value = it.message
                    }
                    if (it.message != "Your request was denied.") {
                        Handler(Looper.myLooper()!!).postDelayed(Runnable {
                            getRequestApi(sendRequest)
                        }, 2000)
                    } else {
                        vm.error.value = it.message
                    }

                }
            }
        })

        vm.obrVerifyToken.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showProgressDialog(R.string.plz_wait)
                }
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    vm.success.value = it.message
                    startNewActivity(DetailActivity().newIntent(this, type))

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


    }

    private fun registerApi() {
        val rqMap: MutableMap<String, String> = HashMap()
        rqMap["username"] = binding.edtUsername.text.toString()
        rqMap["secretkey"] = "ahVLpSF17k"
        rqMap["login_type"] = type.toString()
        rqMap["request_json"] = "full_name,gender,birthdate,phone_number,email,website,profile_picture,address,country"
        viewModel.register("Bearer " + Prefs.getString(Constants.PrefsKeys.AUTHENTICATION, "").replace("\"", ""), rqMap)
    }

    private fun getRequestApi(send_token: String) {
        val rqMap: MutableMap<String, String> = HashMap()
        rqMap["send_request"] = send_token
        viewModel.getRequest("Bearer " + Prefs.getString(Constants.PrefsKeys.AUTHENTICATION, "").replace("\"", ""), rqMap)
    }

    private fun verifyTokenApi(type: Int) {
        val rqMap: MutableMap<String, String> = HashMap()
        rqMap["token"] = binding.edtUsername.text.toString()
        rqMap["type"] = type.toString()
        rqMap["secretkey"] = "ahVLpSF17k"
        viewModel.verifyToken("Bearer " + Prefs.getString(Constants.PrefsKeys.AUTHENTICATION, "").replace("\"", ""), rqMap)
    }

    private fun callPayment() {
        if (isEmpty(binding.edUserName.text.toString())) {
            viewModel.warn.value = "Please enter User Name";
        } else if (isEmpty(binding.edProductId.text.toString())) {
            viewModel.warn.value = "Please enter Product Id";
        } else if (isEmpty(binding.edProductName.text.toString())) {
            viewModel.warn.value = "Please enter Product Name";
        } else if (isEmpty(binding.edProductDescription.text.toString())) {
            viewModel.warn.value = "Please enter Product Description";
        } else if (isEmpty(binding.edProductPrice.text.toString())) {
            viewModel.warn.value = "Please enter Product Price";
        } else if (isEmpty(binding.edProducyDisscount.text.toString())) {
            viewModel.warn.value = "Please enter Product Disscount";
        } else if (isEmpty(binding.edFinalAmmount.text.toString())) {
            viewModel.warn.value = "Please enter Final Amount";
        } else if (isEmpty(binding.edSsubTotal.text.toString())) {
            viewModel.warn.value = "Please enter Sub Total";
        } else if (isEmpty(binding.edTaxAmmount.text.toString())) {
            viewModel.warn.value = "Please enter Tax amount";
        } else if (isEmpty(binding.edTotalAmmount.text.toString())) {
            viewModel.warn.value = "Please enter Total amount";
        } else if (isEmpty(binding.edOrderId.text.toString())) {
            viewModel.warn.value = "Please enter Order Id";
        } else {
            var purchaseData = ArrayList<PurchaseData>()
            purchaseData.add(
                PurchaseData(
                    amount = binding.edTotalAmmount.text.toString(),
                    description = binding.edProductDescription.text.toString(),
                    discount = binding.edProducyDisscount.text.toString(),
                    productId = binding.edProductId.text.toString(),
                    finalAmount = binding.edFinalAmmount.text.toString(),
                    title = binding.edUserName.text.toString()
                )
            )
            val data = PaymentRequestData(
                loginType = type.toString(),
                orderId = binding.edOrderId.text.toString(),
                purchaseData,
                "full_name,phone_number,profile_picture,email",
                Constants.KEY.SECRET_KEY, Constants.KEY.SECRET_TOKEN,
                binding.edSsubTotal.text.toString(),
                binding.edTaxAmmount.text.toString(),
                totalAmount = binding.edTotalAmmount.text.toString(),
                username = binding.edUserName.text.toString()
            )
            viewModel.sendPaymentData("Bearer " + Prefs.getString(Constants.PrefsKeys.AUTHENTICATION, "").replace("\"", ""), data)

        }
    }
}