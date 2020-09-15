package com.sunshine.mylibrary.network

import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.Executor


/**
 * @author SunShine-Joex
 * @date   2020-09-14
 * @desc
 */

class ErrorHandlingAdapter {
    /** A callback which offers granular callbacks for various conditions. */
    interface MyCallback<T> {
        /** Called for [200, 300) responses. */
        fun success(response: Response<T>);

        /** Called for 401 responses. */
        fun unauthenticated(response: Response<*>)

        /** Called for [400, 500) responses, except 401. */
        fun clientError(response: Response<*>)

        /** Called for [500, 600) response. */
        fun serverError(response: Response<*>)

        /** Called for network errors while making the call. */
        fun networkError(e: IOException)

        /** Called for unexpected errors while making the call. */
        fun unexpectedError(t: Throwable)
    }

    interface MyCall<T> {
        fun cancel()
        fun enqueue(callback: MyCallback<T>?)
        fun clone(): MyCall<T> // Left as an exercise for the reader...
        // TODO MyResponse<T> execute() throws MyHttpException;
    }

    class ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != MyCall::class.java) {
                return null
            }
            check(returnType is ParameterizedType) { "MyCall must have generic type (e.g., MyCall<ResponseBody>)" }
            val responseType =
                getParameterUpperBound(
                    0,
                    returnType as ParameterizedType
                )
            val callbackExecutor: Executor? = retrofit.callbackExecutor()
            return ErrorHandlingCallAdapter<Any>(responseType, callbackExecutor)
        }

    }

    class ErrorHandlingCallAdapter<R>(
        val responseType: Type,
        val callbackExecutor: Executor?
    ) : CallAdapter<R, MyCall<R>> {

        override fun adapt(call: Call<R>): MyCall<R> = MyCallAdapter(call, callbackExecutor)

        override fun responseType() = responseType

    }
}

class MyCallAdapter<T>(
    val call: Call<T>,
    val callbackExecutor: Executor?
) : ErrorHandlingAdapter.MyCall<T> {

    override fun cancel() {
        call.cancel()
    }

    override fun clone(): ErrorHandlingAdapter.MyCall<T> {
        return MyCallAdapter(call!!.clone(), callbackExecutor)
    }

    override fun enqueue(callback: ErrorHandlingAdapter.MyCallback<T>?) {
        call!!.enqueue(
            object : Callback<T> {
                override fun onResponse(
                    call: Call<T>?,
                    response: Response<T>
                ) {
                    val code = response.code()
                    when (code) {
                        in 200..299 -> callback!!.success(response)
                        401 -> callback!!.unauthenticated(response)
                        in 400..499 -> callback!!.clientError(response)
                        in 500..599 -> callback!!.serverError(response)
                        else -> callback!!.unexpectedError(RuntimeException("Unexpected response $response"))

                    }
                }

                override fun onFailure(
                    call: Call<T>?,
                    t: Throwable?
                ) {
                    if (t is IOException) {
                        callback!!.networkError((t as IOException?)!!)
                    } else {
                        callback!!.unexpectedError(t!!)
                    }
                }
            })
    }

}


