@file:JvmName("DimensionExt")

package com.sunshine.mylibrary.ext

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

/**
 * @author SunShine-Joex
 * @date   2020-09-07
 * @desc   尺寸转化扩展
 */

/**
 *  dp to px
 */
val Float.dp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 *  px to dp
 */
val Float.px2dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 *  sp to px
 */
val Float.sp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 *  px to sp
 */
val Float.px2sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * 屏幕宽高
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels