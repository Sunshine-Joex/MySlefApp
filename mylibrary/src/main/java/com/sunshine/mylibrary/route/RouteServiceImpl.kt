package com.sunshine.mylibrary.route

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.sunshine.mylibrary.ROUTE_SERVICE
import com.sunshine.mylibrary.ext.route

/**
 * @author SunShine-Joex
 * @date   2020-09-08
 * @desc
 */
@Route(path = ROUTE_SERVICE)
class RouteServiceImpl : RouteService {

    override fun init(context: Context?) {

    }

    override fun openPage(context: Context, url: String, params: MutableMap<String, String>?) {
        when {
            url.startsWith("http") -> { //open WebView
            }
            url.startsWith("/") -> route(
                url,
                params
            ) //native page
            else->{
            }
        }
    }

}