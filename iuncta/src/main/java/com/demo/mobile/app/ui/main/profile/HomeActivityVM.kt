package com.demo.mobile.app.ui.main.profile

import com.demo.mobile.app.data.beans.AccessTokenResponse
import com.demo.mobile.app.data.beans.RegisterResponse
import com.demo.mobile.app.data.remote.helper.ApiCallback
import com.demo.mobile.app.data.remote.helper.NetworkErrorHandler
import com.demo.mobile.app.data.remote.helper.Resource
import com.demo.mobile.app.data.repo.welcome.WelcomeRepo
import com.demo.mobile.app.di.base.viewmodel.BaseViewModel
import com.demo.mobile.app.util.event.SingleRequestEvent
import retrofit2.Response
import javax.inject.Inject

class HomeActivityVM @Inject constructor(private val welcomeRepo: WelcomeRepo, private val networkErrorHandler: NetworkErrorHandler) : BaseViewModel() {

    val obrAccessToken = SingleRequestEvent<AccessTokenResponse>()
    val createprofileObr = SingleRequestEvent<AccessTokenResponse>()

    fun createAccessToken(data: Map<String, String>) {
        welcomeRepo.createAccessToken(data, object : ApiCallback<Response<AccessTokenResponse>>() {
            override fun onSuccess(response: Response<AccessTokenResponse>) {
                obrAccessToken.value = Resource.success(response.body(), response.body()?.message ?: "")
            }

            override fun onFailed(message: String) {
                obrAccessToken.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                obrAccessToken.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                obrAccessToken.value = Resource.loading(null, "")
            }
        })

    }

    fun createToken(data: Request.CreateToken) {
        welcomeRepo.requestUserVerify(data, object : ApiCallback<Response<AccessTokenResponse>>() {
            override fun onSuccess(response: Response<AccessTokenResponse>) {
                createprofileObr.value = Resource.success(response.body(), response.body()?.message ?: "")
            }

            override fun onFailed(message: String) {
                createprofileObr.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                createprofileObr.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                createprofileObr.value = Resource.loading(null, "")
            }
        })

    }
}