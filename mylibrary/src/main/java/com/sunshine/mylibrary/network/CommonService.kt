package com.sunshine.mylibrary.network

import com.sunshine.mylibrary.bean.HttpResult
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author SunShine-Joex
 * @date   2020-09-10
 * @desc
 */
interface CommonService {

    @GET("/wxarticle/chapters/json")
   suspend fun getData(): HttpResult<MutableList<TextBean>>
}