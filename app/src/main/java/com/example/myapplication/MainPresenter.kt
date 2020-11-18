package com.example.myapplication

import com.sunshine.mylibrary.base.BasePresenter
import com.sunshine.mylibrary.bean.LoginParam
import com.sunshine.mylibrary.ext.showToast
import com.sunshine.mylibrary.network.commonApi
import kotlinx.coroutines.launch
import com.sunshine.mylibrary.network.await


/**
 * @author SunShine-Joex
 * @date   2020-09-09
 * @desc
 */
class MainPresenter : BasePresenter<MainActivity>() {

    fun test() {
        val job = launch {
//            view?.onSuccess(commonApi.getData())
//            commonApi.login(LoginParam("qly", "123456"))
            commonApi.loginForm(hashMapOf("username" to "qly", "password" to "123456"))
        }
    }
}