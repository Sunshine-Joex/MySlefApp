@file:JvmName("RouteExt")

package com.sunshine.mylibrary.ext

import android.content.Context
import android.text.TextUtils
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.example.mylibrary.RouteService

/**
 * @author SunShine-Joex
 * @date   2020-09-07
 * @desc   阿里ARouter跳转
 */

/**
 * ARouter 跳转原生页
 */

fun route(routeUrl: String) {
    if (TextUtils.isEmpty(routeUrl)) return
    ARouter.getInstance().build(routeUrl).navigation()
}


/**
 * ARouter 携带参数 跳转原生页
 */

fun route(routeUrl: String, params: MutableMap<String, String>? = mutableMapOf()) {
    if (TextUtils.isEmpty(routeUrl)) return
    val postcard: Postcard = ARouter.getInstance().build(routeUrl)
    params?.forEach {
        postcard.withString(it.key, it.value)
    }
    postcard.navigation()
}

/**
 * ARouter 跳转 H5|原生
 */

fun routePage(
    context: Context, path: String,
    params: MutableMap<String, String>? = mutableMapOf()
) {
    if (TextUtils.isEmpty(path)) return
    ARouter.getInstance().navigation(RouteService::class.java)
        .openPage(context, path, params)
}
