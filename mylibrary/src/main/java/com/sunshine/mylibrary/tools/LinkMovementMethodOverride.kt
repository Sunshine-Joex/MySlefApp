package com.sunshine.mylibrary.tools

import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView

/**
 * @author SunShine-Joex
 * @date   2020/12/4
 * @desc   重写触摸事件的LinkMovementMethod
 */
class LinkMovementMethodOverride : OnTouchListener {

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (v !is TextView) {
            return false
        }
        val widget = v
        val text = widget.text as? Spanned ?: return false
        val action = event.action
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            var x = event.x.toInt()
            var y = event.y.toInt()
            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop
            x += widget.scrollX
            y += widget.scrollY
            val layout = widget.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())
            val links = text.getSpans(
                off, off,
                ClickableSpan::class.java
            )
            if (links.isNotEmpty()) {
                val link = links[0]
                if (action == MotionEvent.ACTION_UP) {
                    link.onClick(widget)
                }
                return true
            }
        }
        return false
    }
}