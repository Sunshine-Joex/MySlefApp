package com.sunshine.mylibrary.base

import android.content.res.Configuration
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @author SunShine-Joex
 * @date   2020-09-09
 * @desc
 */
interface BaseView<out P : BasePresenter<BaseView<P>>> {

}

abstract class BasePresenter<out V : BaseView<BasePresenter<V>>> : CoroutineScope {

    var view: @UnsafeVariance V? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}