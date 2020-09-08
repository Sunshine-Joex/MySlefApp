package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter


/**
 * @author SunShine-Joex
 * @date   2020-08-14
 * @desc
 */
class MyPagerAdapter(
    private val data: List<Int>
) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate: View = LayoutInflater.from(container.context)
            .inflate(R.layout.cardviewpager_item, container, false)
        (inflate.findViewById(R.id.img_card_item) as ImageView).also {
            it.setImageResource(data[position])
        }
        container.addView(inflate)
        return inflate
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}