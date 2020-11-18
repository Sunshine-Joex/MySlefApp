package com.sunshine.mylibrary.network

import com.google.gson.Gson
import com.sunshine.mylibrary.bean.HttpResult
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset

/**
 * @author SunShine-Joex
 * @date   2020/11/17
 * @desc   网络请求拦截器，统一处理response、request
 */
val UTF_8: Charset? = Charset.forName("UTF-8")

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //  header setting
        val builder = request.newBuilder()
        builder.addHeader("env", "test")
        request = builder.build()
        val response = chain.proceed(request)
        val httpResult = bodyToJson(response)
        if (httpResult != null) {
            when (httpResult?.errorCode) {//业务code判断
                in 200..299, 0 -> "callback data success"
                401 -> "unauthenticated:${httpResult.errorMsg}"
                in 400..499 -> "client error:${httpResult?.errorMsg}"
                in 500..599 -> "server error:${httpResult?.errorMsg}"
                else -> "unexpected error:${httpResult?.errorMsg}"
            }

        } else {
            when (response.code) {//http协议code判断
                in 300..399 -> "redirect "
                401 -> "unauthenticated"
                in 400..499 -> "client error"
                in 500..599 -> "server error"
                else -> "unexpected error"
            }
        }
        return response
    }

    private fun bodyToJson(response: Response): HttpResult<*>? {
        val responseBody = response.body
        if (response.isSuccessful && responseBody != null) {// [200..300)
            val source = responseBody.source()
            // Buffer the entire body.
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer
            var charset: Charset? = UTF_8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF_8)
            }
            val bodyString = buffer.clone().readString(charset!!)
            val httpResult = Gson().fromJson(bodyString, HttpResult::class.java)
            return httpResult
        }
        return null
    }

}