package com.example.mylibrary

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route

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
            url.startsWith("/") -> route(url, params) //native page
            else->{
            }
        }
    }

}