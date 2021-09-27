package com.demo.mobile.app.ui.welcome.section.register

import com.demo.mobile.app.data.beans.GetRequestResponse
import com.demo.mobile.app.data.beans.RegisterResponse
import com.demo.mobile.app.data.beans.VerifyTokenResponse
import com.demo.mobile.app.data.remote.helper.ApiCallback
import com.demo.mobile.app.data.remote.helper.NetworkErrorHandler
import com.demo.mobile.app.data.remote.helper.Resource
import com.demo.mobile.app.data.repo.welcome.WelcomeRepo
import com.demo.mobile.app.di.base.viewmodel.BaseViewModel
import com.demo.mobile.app.ui.main.profile.PaymentRequestData
import com.demo.mobile.app.ui.main.profile.Request
import com.demo.mobile.app.util.event.SingleRequestEvent
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class RegisterActivityVM @Inject constructor(private val welcomeRepo: WelcomeRepo, private val networkErrorHandler: NetworkErrorHandler) : BaseViewModel() {

    val obrRegister = SingleRequestEvent<RegisterResponse>()
    val obrGetRequest = SingleRequestEvent<GetRequestResponse>()
        val obrVerifyToken = SingleRequestEvent<VerifyTokenResponse>()
    val obrSendPaymentData = SingleRequestEvent<RegisterResponse>()

    fun register(header: String, req: Map<String, String>) {
        welcomeRepo.requestUserVerify(header, req, object : ApiCallback<Response<RegisterResponse>>() {
            override fun onSuccess(response: Response<RegisterResponse>) {
                obrRegister.value = Resource.success(response.body(), "")
            }

            override fun onFailed(message: String) {
                obrRegister.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                obrRegister.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                obrRegister.value = Resource.loading(null, "")
            }
        })

    }

    fun getRequest(header: String, req: Map<String, String>) {
        welcomeRepo.getRequest(header, req, object : ApiCallback<Response<GetRequestResponse>>() {
            override fun onSuccess(response: Response<GetRequestResponse>) {
                obrGetRequest.value = Resource.success(response.body(), "")
            }

            override fun onFailed(message: String) {
                obrGetRequest.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                obrGetRequest.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                obrGetRequest.value = Resource.loading(null, "")
            }
        })
    }

    fun verifyToken(header: String, req: Map<String, String>) {
        welcomeRepo.verifyToken(header, req, object : ApiCallback<Response<VerifyTokenResponse>>() {
            override fun onSuccess(response: Response<VerifyTokenResponse>) {
                obrVerifyToken.value = Resource.success(response.body(), "")
            }

            override fun onFailed(message: String) {
                obrVerifyToken.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                obrVerifyToken.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                obrVerifyToken.value = Resource.loading(null, "")
            }
        })
    }


    fun sendPaymentData(header: String, data: PaymentRequestData) {
        welcomeRepo.requestUserVerifyForPayment(header,data , object : ApiCallback<Response<RegisterResponse>>() {
            override fun onSuccess(response: Response<RegisterResponse>) {
                obrRegister.value = Resource.success(response.body(), "")
            }

            override fun onFailed(message: String) {
                obrRegister.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                obrRegister.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                obrRegister.value = Resource.loading(null, "")
            }
        })
    }
}