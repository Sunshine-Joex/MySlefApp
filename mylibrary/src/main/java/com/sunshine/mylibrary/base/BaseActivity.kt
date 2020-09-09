package com.sunshine.mylibrary.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sunshine.mylibrary.ext.yes
import com.sunshine.mylibrary.tools.clickSingle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.greenrobot.eventbus.EventBus
import java.lang.reflect.ParameterizedType
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<out P : BasePresenter<BaseActivity<P>>> : AppCompatActivity(),
    BaseView<P>, CoroutineScope, View.OnClickListener {

    val presenter: P
    abstract var layoutId: Int

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        presenter = createPresenter().newInstance()
        presenter.view = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initEventBus()
    }

    private fun createPresenter(): Class<P> {
        var thisClass: Class<*> = this.javaClass
        while (true) {
            (thisClass.genericSuperclass as? ParameterizedType)
                ?.actualTypeArguments
                ?.firstOrNull()
                ?.let {
                    return it as Class<P>
                }?.run {
                    thisClass = thisClass.superclass ?: throw IllegalAccessException()
                }
        }

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
     * 过滤重复点击
     */
    override fun onClick(v: View?) {
        if (clickSingle()) {
            clickView(v)
        }
    }

    /**
     * 派生类重写点击事件
     */
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
