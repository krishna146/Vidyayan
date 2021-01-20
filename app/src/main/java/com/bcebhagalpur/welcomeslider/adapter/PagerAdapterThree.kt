package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bcebhagalpur.welcomeslider.R

class PagerAdapterThree(private val context: Context): PagerAdapter()
{
    companion object {
        val list = arrayOf(
            R.drawable.tp,
            R.drawable.tp,
            R.drawable.tp,
            R.drawable.tp,
            R.drawable.tp,
            R.drawable.tp
        )
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image= ImageView(context)
        image.scaleType= ImageView.ScaleType.CENTER_CROP
        image.setImageResource(list[position])
        container.addView(image,0)
        return image
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}