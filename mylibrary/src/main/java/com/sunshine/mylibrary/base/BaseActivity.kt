package com.sunshine.mylibrary.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bianxianmao.framework.ext.yes
import com.sunshine.mylibrary.tools.clickSingle
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    abstract var layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initEventBus()
    }

    /**
     * 注册点击事件
     */
    open fun registerClicks(vararg args: View?) {
        args.forEach {
            it?.setOnClickListener(this)
        }
    }

    /**
     * 子类不要重写这个方法
     */
    override fun onClick(v: View?) {
        if (clickSingle()) {
            clickView(v)
        }
    }

    open fun clickView(view: View?) {

    }

    private fun initEventBus() {
        useEventBus().yes {
            EventBus.getDefault().register(this)
        }
    }

    protected open fun useEventBus() = false

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
