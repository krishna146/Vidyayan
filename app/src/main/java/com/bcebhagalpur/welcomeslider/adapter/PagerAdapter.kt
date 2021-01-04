package com.bcebhagalpur.welcomeslider.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bcebhagalpur.welcomeslider.fragment.WelcomeFragment1
import com.bcebhagalpur.welcomeslider.fragment.WelcomeFragment2
import com.bcebhagalpur.welcomeslider.fragment.WelcomeFragment3

class PagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    val list= arrayOf(WelcomeFragment1(),WelcomeFragment2(),WelcomeFragment3())

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }
}