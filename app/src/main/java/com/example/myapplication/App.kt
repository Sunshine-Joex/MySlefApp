package com.example.myapplication

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.sunshine.mylibrary.helper.ContextHelper

/**
 * @author SunShine-Joex
 * @date   2020-09-07
 * @desc
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ContextHelper.init(this)

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}