package com.demo.mobile.app.data.beans

import com.demo.mobile.app.BuildConfig

object Constants {

    const val optionHome = 1
    const val optionProfile = 2
    const val optionSettings = 3
    const val optionHelp = 4
    const val appId = "com.demo.mobile.app"


    object KEY{
        var SECRET_KEY: String = ""
        var SECRET_TOKEN: String = ""
    }


    object Menu {
        const val ABOUT: String = "About"
        const val SHOP: String = "Shop"
        const val SETTINGS: String = "Settings"
        const val LOG_OUT: String = "Log out"
    }

    object PrefsKeys {
        const val USER_ID: String = "USER_ID"
        const val USER_DATA: String = "USER_DATA"
        const val AUTHENTICATION: String = "AUTHENTICATIONS"
        const val SEND_REQUEST: String = "SEND_REQUEST"
    }

    object Gender {
        const val GENDER_MALE: Int = 0
        const val GENDER_FEMALE: Int = 1
        const val strMale: String = "Male"
        const val strFemale: String = "Female"
    }

    object Broadcast {
        const val id: String = "id"
    }

    object Extra {
        const val DATE_TIME_FORMAT: String = "yyyy/MM/dd HH:mm:ss"
    }

    object URLs {
        const val BaseUrl: String = BuildConfig.BASE_URL
    }

}