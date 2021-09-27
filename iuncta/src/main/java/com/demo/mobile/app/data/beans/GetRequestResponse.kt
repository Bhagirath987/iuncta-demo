package com.demo.mobile.app.data.beans

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetRequestResponse(

    @field:SerializedName("data")
    val data: GetRequestData = GetRequestData(),

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Serializable

data class GetRequestData(

    @field:SerializedName("full_name")
    val fullName: String = "",
    @field:SerializedName("gender")
    val gender: String = "",
    @field:SerializedName("birthdate")
    val birthdate: String = "",
    @field:SerializedName("phone_number")
    val phone_number: String = "",
    @field:SerializedName("email")
    val email: String = "",
    @field:SerializedName("website")
    val website: String = "",
    @field:SerializedName("profile_picture")
    val profile_picture: String = "",
    @field:SerializedName("address")
    val address: String = "",
    @field:SerializedName("country")
    val country: String = "",
    @field:SerializedName("country_name")
    val country_name: String = "",
    @field:SerializedName("state")
    val state: String = "",
    @field:SerializedName("state_name")
    val state_name: String = "",
    @field:SerializedName("city")
    val city: String = "",
    @field:SerializedName("city_name")
    val city_name: String = "",
    @field:SerializedName("company_name")
    val company_name: String = "",
    @field:SerializedName("position")
    val position: String = "",
    @field:SerializedName("order_id")
    val order_id: String = "",
    @field:SerializedName("amount")
    val amount: String = "",
    @field:SerializedName("transaction_id")
    val transaction_id: String = "",
    @field:SerializedName("payment_status")
    val payment_status: String = "",
) : Serializable