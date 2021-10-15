package com.demo.mobile.app.ui.main.profile
import com.google.gson.annotations.SerializedName

class Request {
data class CreateToken(
    @SerializedName("secretkey")
    var secretkey: String? = "",
    @SerializedName("secrettoken")
    var secrettoken: String? = ""
)
}

class PaymentRequest : ArrayList<PaymentRequestItem>()

data class PaymentRequestItem(
    @SerializedName("amount")
    var amount: Int? = 0,
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("discount")
    var discount: Int? = 0,
    @SerializedName("final_amount")
    var finalAmount: Int? = 0,
    @SerializedName("product_id")
    var productId: String? = "",
    @SerializedName("title")
    var title: String? = ""
)

    data class PaymentRequestData(
        @SerializedName("login_type")
        var loginType: String? = "",
        @SerializedName("order_id")
        var orderId: String? = "",
        @SerializedName("purchase")
        var purchaseData: List<PurchaseData>? = listOf(),
        @SerializedName("request_json")
        var requestJson: String? = "",
        @SerializedName("secretkey")
        var secretkey: String? = "",
        @SerializedName("secrettoken")
        var secrettoken: String? = "",
        @SerializedName("sub_total")
        var subTotal: String? = "",
        @SerializedName("tax_amount")
        var taxAmount: String? = "",
        @SerializedName("total_amount")
        var totalAmount: String? = "",
        @SerializedName("username")
        var username: String? = ""
    )

data class PurchaseData(
    @SerializedName("amount")
    var amount: String? = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("discount")
    var discount: String? = "",
    @SerializedName("final_amount")
    var finalAmount: String? = "",
    @SerializedName("product_id")
    var productId: String? = "",
    @SerializedName("title")
    var title: String? = ""
)