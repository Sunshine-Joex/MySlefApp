@file:JvmName("ToastExt")

package com.sunshine.mylibrary.ext

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.sunshine.mylibrary.helper.ContextHelper
import java.lang.reflect.Proxy

/**
 * @author SunShine-Joex
 * @date   2020-09-15
 * @desc
 */


private var toast: Toast? = null

private var iNotificationManagerObj: Any? = null

fun showToast(toastText: String = "", toastTime: Int = Toast.LENGTH_SHORT) {
    if (toast == null) {
        toast = Toast.makeText(ContextHelper.context, toastText, toastTime)
    }
    toast?.show()
}

fun isNotificationEnabled() =
    NotificationManagerCompat.from(ContextHelper.context!!).areNotificationsEnabled()

/**
 * API 29报错（系统不能通过反射获取）
 */
@SuppressLint("SoonBlockedPrivateApi")
fun invokeToast(toast: Toast): Toast {
    try {
        val getServiceMethod =
            Toast::class.java.getDeclaredMethod("getService")
        getServiceMethod.isAccessible = true
        //hook INotificationManager
        if (iNotificationManagerObj == null) {
            iNotificationManagerObj =
                getServiceMethod.invoke(null)
            val iNotificationManagerCls =
                Class.forName("android.app.INotificationManager")
            val iNotificationManagerProxy = Proxy.newProxyInstance(
                toast.javaClass.classLoader,
                arrayOf(iNotificationManagerCls)
            ) { proxy, method, args ->
                //强制使用系统Toast
                if ("enqueueToast" == method.name || "enqueueToastEx" == method.name) { //华为p20 pro上为enqueueToastEx
                    args[0] = "android"
                }
                method.invoke(
                    iNotificationManagerObj,
                    *args
                )
            }
            val serviceFiled =
                Toast::class.java.getDeclaredField("sService")
            serviceFiled.isAccessible = true
            serviceFiled[null] = iNotificationManagerProxy
        }
        return toast
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return toast
}
