package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.fragment.WelcomeFragment1
import com.bcebhagalpur.welcomeslider.fragment.WelcomeFragment2
import com.bcebhagalpur.welcomeslider.fragment.WelcomeFragment3

class PagerAdapterTwo(private val context: Context): PagerAdapter()
{
    companion object {
        val list = arrayOf(
            R.drawable.intro_one,
            R.drawable.twelve,
            R.drawable.seven,
            R.drawable.six,
            R.drawable.intro_three,
            R.drawable.eight
        )
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
      return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image=ImageView(context)
        image.scaleType=ImageView.ScaleType.CENTER_CROP
        image.setImageResource(list[position])
        container.addView(image,0)
        return image
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}