package com.example.myapplication

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author SunShine-Joex
 * @date   2020-09-07
 * @desc
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}