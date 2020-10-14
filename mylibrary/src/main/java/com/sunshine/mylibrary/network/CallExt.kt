@file:JvmName("CallExt")

package com.sunshine.mylibrary.network

import com.sunshine.mylibrary.bean.HttpResult
import com.sunshine.mylibrary.ext.showToast
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.*
import kotlin.coroutines.resume

/**
 * @author SunShine-Joex
 * @date   2020-09-15
 * @desc   Copy Retrofit resource code KotlinExtensions.kt
 */
suspend fun <T : Any> Call<T>.await(): T {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {// [200..300)
                    val body = response.body()
                    if (body != null) {
                        val httpResult = body as HttpResult<*>
                        when (httpResult.errorCode) {
                            0 -> continuation.resume(body)
                            in 200..299 -> continuation.resume(body)
                            401 -> "unauthenticated:${httpResult.errorMsg}"
                            in 400..499 -> "client error:${httpResult.errorMsg}"
                            in 500..599 -> "server error:${httpResult.errorMsg}"
                            else -> "unexpected error:${httpResult.errorMsg}"
                        }
                    } else {
                        val invocation = call.request().tag(Invocation::class.java)!!
                        val method = invocation.method()
                        val e = KotlinNullPointerException(
                            "Response from " +
                                    method.declaringClass.name +
                                    '.' +
                                    method.name +
                                    " was null but response body type was declared as non-null"
                        )
                    }
                } else {
                    when (response.code()) {
                        in 300..399 -> "redirect "
                        401 -> "unauthenticated"
                        in 400..499 -> "client error"
                        in 500..599 -> "server error"
                        else -> "unexpected error"
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {

            }
        })
    }
}
