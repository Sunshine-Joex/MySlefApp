package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text.text = "$screenWidth--$screenHeight"

        tv_text.setOnClickListener { routePage(this, TEST) }


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
