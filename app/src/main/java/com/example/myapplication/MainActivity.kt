package com.example.myapplication

import android.os.Bundle
import android.view.View
import com.sunshine.mylibrary.base.BaseActivity
import com.sunshine.mylibrary.bean.HttpResult
import com.sunshine.mylibrary.network.TextBean
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainPresenter>() {


    override var layoutId: Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerClicks(top, bottom)
    }

    override fun clickView(view: View?) {
        super.clickView(view)
        when (view) {
            top -> presenter.test()
        }
    }

    fun onSuccess(data: HttpResult<MutableList<TextBean>>) {
        bottom.text = data.data?.get(0)?.name ?: ""
    }


}
