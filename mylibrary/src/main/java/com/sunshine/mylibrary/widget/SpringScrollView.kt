package com.sunshine.mylibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView
import androidx.dynamicanimation.animation.SpringAnimation

/**
 * @author SunShine-Joex
 * @date   2020/11/9
 * @desc   带有弹性效果的NestedScrollView
 */
class SpringScrollView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : NestedScrollView(context!!, attrs, defStyle) {

    private var startDragY = 0f
    private val springAnim: SpringAnimation = SpringAnimation(
        this, SpringAnimation.TRANSLATION_Y,
        0F
    )

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_MOVE -> if (scrollY <= 0) {
                //顶部下拉
                if (startDragY == 0f) {
                    startDragY = e.rawY
                }
                if (e.rawY - startDragY > 0) {
                    translationY = (e.rawY - startDragY) / 3
                    return true
                } else {
                    springAnim.cancel()
                    translationY = 0f
                }
            } else if (scrollY + height >= getChildAt(0).measuredHeight) {
                //底部上拉
                if (startDragY == 0f) {
                    startDragY = e.rawY
                }
                if (e.rawY - startDragY < 0) {
                    translationY = (e.rawY - startDragY) / 3
                    return true
                } else {
                    springAnim.cancel()
                    translationY = 0f
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (translationY != 0f) {
                    springAnim.start()
                }
                startDragY = 0f
            }
        }
        return super.onTouchEvent(e)
    }

    init {
        //刚度 默认1200 值越大回弹的速度越快
        springAnim.spring.stiffness = 200.0f
        //阻尼 默认0.5 值越小，回弹之后来回的次数越多
        springAnim.spring.dampingRatio = 0.90f
    }
}