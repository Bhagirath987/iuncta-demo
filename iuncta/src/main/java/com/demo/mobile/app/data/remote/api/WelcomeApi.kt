package com.demo.mobile.app.data.remote.api

import com.demo.mobile.app.data.beans.*
import com.demo.mobile.app.data.beans.base.ApiResponse
import com.demo.mobile.app.ui.main.profile.PaymentRequestData
import com.demo.mobile.app.ui.main.profile.Request
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface WelcomeApi {

    @POST(EndPoints.Auth.login)
    fun loginAsync(@Body request: RegisterRequest): Deferred<Response<ApiResponse<UserBean>>>

    @Multipart
    @POST(EndPoints.Auth.register)
    fun registerAsync(@PartMap data: Map<String, @JvmSuppressWildcards RequestBody>, @Part profilePhoto: MultipartBody.Part): Deferred<Response<ApiResponse<UserBean>>>

    @FormUrlEncoded
    @POST(EndPoints.Auth.refresh_token)
    fun refreshTokenNewAsync(@Field("refreshToken") refreshToken: String): Deferred<Response<ApiResponse<Authentication>>>

    @POST(EndPoints.Auth.create_access_token)
    fun createAccessTokenAsync(@Body data: Map<String, String>): Deferred<Response<AccessTokenResponse>>

    /*@Multipart
    @POST(EndPoints.Auth.request_user_verify)
    fun requestUserVerifyAsync(@PartMap data: Map<String, @JvmSuppressWildcards RequestBody>): Deferred<Response<RegisterResponse>>
*/
    @FormUrlEncoded
    @POST(EndPoints.Auth.request_user_verify)
    fun requestUserVerifyAsync(@Header("Authorization") header: String, @FieldMap data: Map<String, String>): Deferred<Response<RegisterResponse>>

    @POST(EndPoints.Auth.create_access_token)
    fun createTokenRequestAsync(@Body header: Request.CreateToken): Deferred<Response<AccessTokenResponse>>

    @POST(EndPoints.Auth.get_request)
    fun getRequestAsync(@Header("Authorization") header: String, @Body data: Map<String, String>): Deferred<Response<GetRequestResponse>>

    @POST(EndPoints.Auth.verify_token)
    fun verifyTokenAsync(@Header("Authorization") header: String, @Body data: Map<String, String>): Deferred<Response<VerifyTokenResponse>>

    @POST(EndPoints.Auth.request_user_verify)
    fun requestUserVerifyForPaymentAsync(@Header("Authorization") header: String,@Body data: PaymentRequestData): Deferred<Response<RegisterResponse>>

}