package com.demo.mobile.app.di.module

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.demo.mobile.app.BuildConfig
import com.demo.mobile.app.data.remote.helper.NetworkErrorHandler
import com.demo.mobile.app.data.remote.intersepter.AuthorizationInterceptor
import com.demo.mobile.app.data.remote.intersepter.RequestInterceptor
import com.demo.mobile.app.di.qualifier.ApiDateFormat
import com.demo.mobile.app.di.qualifier.ApiEndpoint
import com.demo.mobile.app.util.misc.ConnectionHandler
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {
    @Singleton
    @Provides
    fun gson(@ApiDateFormat apiDateFormat: String): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat(apiDateFormat)
            .create()
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.e("Retrofit", message) }
            .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @ApiEndpoint
    @Singleton
    @Provides
    fun retrofit(
        @ApiEndpoint apiEndpoint: HttpUrl, okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiEndpoint)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            /*.authenticator(TokenAuthenticator())*/
            //.addInterceptor(AuthorizationInterceptor())
            .addInterceptor(RequestInterceptor())
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun connectionUtils(daggerApplication: DaggerApplication): ConnectionHandler {
        return ConnectionHandler(daggerApplication.applicationContext)
    }

    @Singleton
    @Provides
    fun converterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun callAdapterFactory(): CallAdapter.Factory {
        return CoroutineCallAdapterFactory()
    }

    @Singleton
    @Provides
    fun networkErrorHandler(context: DaggerApplication): NetworkErrorHandler {
        return NetworkErrorHandler(context)
    }
}