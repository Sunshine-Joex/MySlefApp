@file:JvmName("GlideExt")

package com.sunshine.mylibrary.ext

import android.widget.ImageView
import androidx.annotation.ColorInt
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.sunshine.mylibrary.tools.glide.*

/**
 * @author SunShine-Joex
 * @date   2020/11/9
 * @desc   glide 加载圆角(边框)、圆形(边框)
 */

fun ImageView.load(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

//加载圆角
fun ImageView.loadCircle(url: String) {
    Glide.with(this.context)
        .load(url)
        .transform(CircleCrop())
        .into(this)
}

//加载圆角带边框
fun ImageView.loadCircleBorder(
    url: String,
    @ColorInt borderColor: Int,
    borderWidth: Int = 1f.dp2px,
) {
    Glide.with(this.context)
        .load(url)
        .transform(CropCircleWithBorderTransformation(borderWidth, borderColor))
        .into(this)
}

//加载圆角图（可以设置任意圆角）
fun ImageView.loadRoundedCorners(
    url: String,
    radius: Int = 10f.dp2px,
    cornerType: RoundedCornersTransformation.CornerType = RoundedCornersTransformation.CornerType.ALL
) {
    Glide.with(this.context)
        .load(url)
        .transform(
            RoundedCornersTransformation(
                radius,
                radius,
                cornerType
            )
        )
        .into(this)
}




