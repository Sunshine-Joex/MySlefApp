package com.sunshine.mylibrary.widget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.RectF
import android.text.style.ReplacementSpan
import android.widget.TextView
import androidx.annotation.NonNull
import com.sunshine.mylibrary.ext.sp2px


/**
 * @author SunShine-Joex
 * @date 2020/11/23
 * @desc TextView 标签开头
 */
class TagSpan(
    private val tv: TextView,
    //背景色
    private val mBgColor: Int,
    //文字颜色
    private val mTextColor: Int,
    //边框颜色
    private val mStrokeColor: Int,
    // 文字大小
    private val mTextSizePx: Int,
    //圆角大小
    private val mRadiusPx: Int,
    //span 和右边文字距离
    private val mRightMarginPx: Int,
    paddingLR: Int,
    paddingTB: Int,
    mode: Boolean
) : ReplacementSpan() {
    private var spanWidth = 0f //span 宽度

    private val paddingLR: Float//左右padding

    private val paddingTB: Float//上下padding

    private val stroke: Boolean //是否是空心矩形


    init {
        this.paddingLR = paddingLR.toFloat()
        this.paddingTB = paddingTB.toFloat()
        stroke = mode
    }

    override fun getSize(
        @NonNull paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: FontMetricsInt?
    ): Int {
        paint.textSize = mTextSizePx.toFloat()
        spanWidth = paint.measureText(text, start, end)
        paint.textSize = tv.textSize //控制高度
        return spanWidth.toInt() + mRightMarginPx + 2 * paddingLR.toInt()
    }

    override fun draw(
        @NonNull canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val rectF = drawTagRect(canvas, x, y, paint)
        drawTagText(canvas, text, start, end, y, paint, rectF)
    }

    private fun drawTagRect(
        canvas: Canvas,
        x: Float,
        y: Int,
        paint: Paint
    ): RectF {
        if (stroke) {
            paint.color = mStrokeColor
            paint.strokeWidth = 1f
            paint.style = Paint.Style.STROKE
        } else {
            paint.color = mBgColor
            paint.style = Paint.Style.FILL
        }
        paint.isAntiAlias = true
        val fontMetrics = paint.fontMetricsInt
        val strokeWidth = paint.strokeWidth
        val oval = RectF(
            x,
            y + fontMetrics.ascent + paddingTB,
            x + spanWidth + 2 * paddingLR,
            y + fontMetrics.descent - paddingTB
        )
        canvas.drawRoundRect(oval, mRadiusPx.toFloat(), mRadiusPx.toFloat(), paint)
        return oval
    }

    private fun drawTagText(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        y: Int,
        paint: Paint,
        rect: RectF
    ) {
        paint.textSize = mTextSizePx.toFloat()
        paint.style = Paint.Style.FILL
        paint.color = mTextColor
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.CENTER
        val fontMetrics = paint.fontMetricsInt
        val textCenterX = spanWidth / 2 + paddingLR
        val textBaselineY =
            (rect.bottom + rect.top - fontMetrics.ascent - fontMetrics.descent) / 2
        val tag = text.subSequence(start, end).toString()
        canvas.drawText(tag, textCenterX, textBaselineY, paint)
    }


}