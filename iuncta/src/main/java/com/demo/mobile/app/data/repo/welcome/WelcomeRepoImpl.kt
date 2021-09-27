package com.demo.mobile.app.data.repo.welcome

import com.demo.mobile.app.data.beans.*
import com.google.gson.Gson
import com.demo.mobile.app.data.beans.base.ApiResponse
import com.demo.mobile.app.data.beans.base.SimpleApiResponse
import com.demo.mobile.app.data.local.SharedPref
import com.demo.mobile.app.data.remote.api.WelcomeApi
import com.demo.mobile.app.data.remote.helper.ApiCallback
import com.demo.mobile.app.di.base.MyApplication
import com.demo.mobile.app.ui.main.profile.PaymentRequestData
import com.demo.mobile.app.ui.main.profile.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class WelcomeRepoImpl(private val welcomeApi: WelcomeApi, private val sharedPref: SharedPref) : WelcomeRepo {

    override fun login(rq: RegisterRequest, apiCallback: ApiCallback<Response<ApiResponse<UserBean>>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.loginAsync(rq)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun register(data: Map<String, RequestBody>, profilePhoto: MultipartBody.Part, apiCallback: ApiCallback<Response<ApiResponse<UserBean>>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.registerAsync(data, profilePhoto)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun refreshTokenNew(apiCallback: ApiCallback<Response<ApiResponse<Authentication>>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.refreshTokenNewAsync(MyApplication.instance?.getUserAuthentication()?.refreshToken.toString())
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun createAccessToken(data: Map<String, String>, apiCallback: ApiCallback<Response<AccessTokenResponse>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.createAccessTokenAsync(data)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun requestUserVerify(header: String, data: Map<String, String>, apiCallback: ApiCallback<Response<RegisterResponse>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.requestUserVerifyAsync(header, data)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun requestUserVerify(header: Request.CreateToken, apiCallback: ApiCallback<Response<AccessTokenResponse>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.createTokenRequestAsync(header)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun getRequest(header: String, data: Map<String, String>, apiCallback: ApiCallback<Response<GetRequestResponse>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.getRequestAsync(header, data)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun verifyToken(header: String, data: Map<String, String>, apiCallback: ApiCallback<Response<VerifyTokenResponse>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.verifyTokenAsync(header, data)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

    override fun requestUserVerifyForPayment(header: String, data: PaymentRequestData, apiCallback: ApiCallback<Response<RegisterResponse>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = welcomeApi.requestUserVerifyForPaymentAsync(header,data)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        val apiRes = SimpleApiResponse()
                        val apiResponse: SimpleApiResponse? = Gson().fromJson(response.errorBody()?.string(), apiRes::class.java)
                        apiResponse?.message?.let { apiCallback.onFailed(it) }
                            ?: let { apiCallback.onFailed("Something went wrong") }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }
}