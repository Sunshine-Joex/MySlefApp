package com.example.myapplication

import com.sunshine.mylibrary.base.BasePresenter
import com.sunshine.mylibrary.network.commonApi
import kotlinx.coroutines.launch

/**
 * @author SunShine-Joex
 * @date   2020-09-09
 * @desc
 */
class MainPresenter : BasePresenter<MainActivity>() {

    fun test() {
        launch {
            view?.onSuccess(commonApi.getData())
        }

    }
}