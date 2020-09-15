package com.sunshine.mylibrary.helper

import android.app.Application
import android.content.Context

/**
 * @author SunShine-Joex
 * @date 2020-09-15
 * @desc
 */
object ContextHelper {
    /**
     * 静态变量保存的application context
     */
    private var applicationContext: Context? = null
    /* Public Methods */
    /**
     * 初始化context，如果由于不同机型导致反射获取context失败可以在Application调用此方法
     * @param context applicationContext
     */
    fun init(context: Context?) {
        applicationContext = context
    }

    /**
     * 反射获取 application context
     */
    val context: Context?
        get() {
            if (null == applicationContext) {
                try {
                    val application =
                        Class.forName("android.app.ActivityThread")
                            .getMethod("currentApplication")
                            .invoke(null, null as Array<Any?>?) as Application
                    if (application != null) {
                        applicationContext = application
                        return application
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    val application =
                        Class.forName("android.app.AppGlobals")
                            .getMethod("getInitialApplication")
                            .invoke(null, null as Array<Any?>?) as Application
                    if (application != null) {
                        applicationContext = application
                        return application
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                throw IllegalStateException("ContextHolder is not initialed, it is recommend to init with application context.")
            }
            return applicationContext
        }
}