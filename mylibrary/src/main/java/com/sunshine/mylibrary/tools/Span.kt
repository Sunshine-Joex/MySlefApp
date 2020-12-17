@file:JvmName("ToolSpan")

package com.sunshine.mylibrary.tools

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.sunshine.mylibrary.ext.dp2px
import com.sunshine.mylibrary.widget.TagSpan

/**
 * @author SunShine-Joex
 * @date   2020/12/4
 * @desc
 */

/* 用法：标签以外的点击事件，交给父view
getSpanClick(tv_span, "谢尔顿", TEXT, Color.parseColor("#222222"), object : ClickableSpan() {
    override fun onClick(widget: View) {
        Toast.makeText(this@MainActivity, "span label", Toast.LENGTH_SHORT).show()
    }

    override fun updateDrawState(@NonNull ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }

})*/
//富文本部分点击，富文本之外可点击 问题
fun getSpanClick(
    tv: TextView,
    start: String,
    content: String,
    color: Int,
    clickableSpan: ClickableSpan
) {
    val spanString = SpannableString(start)
    val span = TagSpan(
        tv,
        Color.RED,
        Color.BLACK,
        Color.GREEN,
        tv.textSize.toInt(),
        2f.dp2px,
        6f.dp2px,
        3f.dp2px,
        2f.dp2px,
        true
    )

    spanString.setSpan(clickableSpan, 0, start.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    spanString.setSpan(
        span,
        0,
        spanString.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
//    spanString.setSpan(span, 0, start.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)//先设置会被覆盖颜色
    tv.setOnTouchListener(LinkMovementMethodOverride()) //span 点击消失bug 、ClickableSpan和onclick冲突问题
    tv.text = spanString
    tv.append(content)
}