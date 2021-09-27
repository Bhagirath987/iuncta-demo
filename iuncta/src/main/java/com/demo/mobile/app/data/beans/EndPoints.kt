package com.demo.mobile.app.data.beans

object EndPoints {

    object Auth {
        const val refresh_token = "refresh-token"
        const val login = "login"
        const val register = "register"
        const val forgot = "forgotPassword"
        const val social_account = "social-account"
        const val update_profile = "user/update-profile"
        const val update_qr_code = "user/update-qr-code"
        const val user = "user/{user}"
        const val logout = "logout"
        const val create_access_token = "create-access-token"
        const val request_user_verify = "request-user-verify"
        const val get_request = "get-request"
        const val verify_token = "verify-token"
    }

    object ForgotPassword {
        const val forgot_password = "forgot-password"
        const val verify = "forgot-password/verify"
        const val reset = "forgot-password/reset"
    }

    object ChangePassword {
        const val change_password = "user/change-password"
        const val change_settings = "setting"
    }

}