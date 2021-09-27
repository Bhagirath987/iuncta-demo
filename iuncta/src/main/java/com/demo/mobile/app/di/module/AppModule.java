package com.demo.mobile.app.di.module;

import android.content.Context;


import com.demo.mobile.app.BuildConfig;
import com.demo.mobile.app.di.qualifier.ApiDateFormat;
import com.demo.mobile.app.di.qualifier.ApiEndpoint;
import com.demo.mobile.app.di.qualifier.AppContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.DaggerApplication;
import okhttp3.HttpUrl;

@Module
public class AppModule {
    @AppContext
    @Provides
    static Context context(DaggerApplication daggerApplication) {
        return daggerApplication;
    }

    @ApiEndpoint
    @Singleton
    @Provides
    static HttpUrl apiEndpoint() {
        return HttpUrl.parse(BuildConfig.BASE_URL);
    }


    @ApiDateFormat
    @Singleton
    @Provides
    static String apiDateFormat() {
        return "yyyy-MM-dd HH:mm:ss";
    }


}
