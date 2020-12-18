package com.sunshine.mylibrary.route

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author SunShine-Joex
 * @date   2020-09-08
 * @desc
 */
interface RouteService : IProvider {
    fun openPage(
        context: Context,
        url: String,
        params: MutableMap<String, String>? = mutableMapOf()
    )
}