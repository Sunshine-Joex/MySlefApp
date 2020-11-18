package com.sunshine.mylibrary.network

import com.sunshine.mylibrary.bean.HttpResult
import com.sunshine.mylibrary.bean.LoginParam
import com.sunshine.mylibrary.bean.TextBean
import retrofit2.http.*

/**
 * @author SunShine-Joex
 * @date   2020-09-10
 * @desc
 */
interface CommonService {

    @GET("/wxarticle/chapters/json")
    suspend fun getData(): HttpResult<MutableList<TextBean>>

    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("/user/login")
    suspend fun login(@Body body: LoginParam): String

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun loginForm(@FieldMap maps: MutableMap<String, Any>): String

}