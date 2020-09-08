package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.*
import com.sunshine.mylibrary.base.BaseActivity
import com.sunshine.mylibrary.ext.routePage
import com.sunshine.mylibrary.ext.screenHeight
import com.sunshine.mylibrary.ext.screenWidth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    override var layoutId: Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_text.text = "$screenWidth--$screenHeight"

        registerClicks(tv_text)

//        cardViewPager.offscreenPageLimit = 3
//        cardViewPager.adapter = MyPagerAdapter(
//            arrayListOf(
//                R.mipmap.transformer,
//                R.mipmap.transformer,
//                R.mipmap.transformer, R.mipmap.transformer,
//                R.mipmap.transformer,
//                R.mipmap.transformer, R.mipmap.transformer,
//                R.mipmap.transformer,
//                R.mipmap.transformer
//            )
//        )
//        cardViewPager.pageMargin = 100
//        cardViewPager.setPageTransformer(true, AlphaAndScalePageTransformer())
    }


    override fun clickView(view: View?) {
        super.clickView(view)
        when (view) {
            tv_text -> routePage(this, TEST)
        }
    }
}
