package com.sunshine.mylibrary.bean

/**
 * @author SunShine-Joex
 * @date   2020-09-11
 * @desc
 */
//data class HttpResult(val errorCode:Int,val errorMsg:String,val data:T)
class HttpResult<T> {
    val errorCode: Int? = null
    val errorMsg: String? = null
    val data: T? = null
}
