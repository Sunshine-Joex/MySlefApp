package com.example.myapplication

import android.os.Bundle
import com.sunshine.mylibrary.base.BaseActivity


class MainActivity : BaseActivity<MainPresenter>() {


    override var layoutId: Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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


}
