package com.demo.mobile.app.data.beans

import com.google.gson.annotations.SerializedName

data class VerifyTokenResponse(

    @field:SerializedName("data")
    val data: VerifyTokenData = VerifyTokenData(),

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class VerifyTokenData(

    @field:SerializedName("created_at")
    val createdAt: String = "",

    @field:SerializedName("is_login")
    val isLogin: Boolean = false
)
