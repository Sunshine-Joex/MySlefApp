@file:JvmName("SingleClick")

package com.sunshine.mylibrary.tools

/**
 * @author SunShine-Joex
 * @date   2019/9/2
 * @desc   去重复点击事件
 */

var clickGapTime = 0L
const val CLICK_GAP_RESPONSE = 500L

fun clickSingle(): Boolean {
    val currentTimeMillis = System.currentTimeMillis()
    if (currentTimeMillis - clickGapTime < CLICK_GAP_RESPONSE) return false
    clickGapTime = currentTimeMillis
    return true
}


