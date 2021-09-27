package com.demo.mobile.app.data.beans

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(

    @field:SerializedName("data")
    val data: AccessTokenData = AccessTokenData(),

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class AccessTokenData(

    @field:SerializedName("accessToken")
    val accessToken: String = ""
)
