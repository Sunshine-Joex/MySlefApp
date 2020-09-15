package com.sunshine.mylibrary.network

import android.util.Log
import com.example.mylibrary.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

/**
 * @author SunShine-Joex
 * @date   2020-09-10
 * @desc
 */

val logger = object : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.i("network", "======$message======")
    }
}

val loggingInterceptor = HttpLoggingInterceptor(logger).setLevel(
    if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
)

val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
//        .addInterceptor(ChuckInterceptor(ContextHolder.getContext()))
        .build()
}
val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val commonApi = retrofit.create(CommonService::class.java)