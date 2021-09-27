package com.demo.mobile.app.data.repo.welcome

import com.demo.mobile.app.data.beans.*
import com.demo.mobile.app.data.beans.base.ApiResponse
import com.demo.mobile.app.data.remote.helper.ApiCallback
import com.demo.mobile.app.ui.main.profile.PaymentRequestData
import com.demo.mobile.app.ui.main.profile.Request
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface WelcomeRepo {

    fun login(rq: RegisterRequest, apiCallback: ApiCallback<Response<ApiResponse<UserBean>>>)

    fun register(data: Map<String, RequestBody>, profilePhoto: MultipartBody.Part, apiCallback: ApiCallback<Response<ApiResponse<UserBean>>>)

    fun refreshTokenNew(apiCallback: ApiCallback<Response<ApiResponse<Authentication>>>)

    fun createAccessToken(data: Map<String, String>, apiCallback: ApiCallback<Response<AccessTokenResponse>>)

    fun requestUserVerify(header: String, data: Map<String, String>, apiCallback: ApiCallback<Response<RegisterResponse>>)

    fun requestUserVerify(header: Request.CreateToken, apiCallback: ApiCallback<Response<AccessTokenResponse>>)

    fun getRequest(header: String, data: Map<String, String>, apiCallback: ApiCallback<Response<GetRequestResponse>>)

    fun verifyToken(header: String, data: Map<String, String>, apiCallback: ApiCallback<Response<VerifyTokenResponse>>)

    fun requestUserVerifyForPayment(header: String, data: PaymentRequestData, apiCallback: ApiCallback<Response<RegisterResponse>>)

}