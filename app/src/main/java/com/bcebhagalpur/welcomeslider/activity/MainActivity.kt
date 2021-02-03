package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.PagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var img1:ImageView
    private lateinit var img2:ImageView
    private lateinit var img3:ImageView
    private lateinit var adapter:PagerAdapter
    private lateinit var btnStart:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)

        viewPager=findViewById(R.id.viewPager)
        btnStart=findViewById(R.id.btnStart)
        img1=findViewById(R.id.img1)
        img2=findViewById(R.id.img2)
        img3=findViewById(R.id.img3)

        adapter= PagerAdapter(supportFragmentManager)
        viewPager.adapter=adapter
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                if (position==2){
                    btnStart.visibility=View.VISIBLE
                    btnStart.setOnClickListener {
                        startActivity(Intent(this@MainActivity,LoginActivity::class.java))
                        finish()
                    }
                }else{
                    btnStart.visibility=View.GONE
                }
                when(viewPager.currentItem){
                    0->{
                        img1.setImageResource(R.drawable.indicator_circle_black)
                        img2.setImageResource(R.drawable.indicator_circle)
                        img3.setImageResource(R.drawable.indicator_circle)
                    }
                    1->{
                        img2.setImageResource(R.drawable.indicator_circle_black)
                        img1.setImageResource(R.drawable.indicator_circle)
                        img3.setImageResource(R.drawable.indicator_circle)
                    }
                    2->{
                        img3.setImageResource(R.drawable.indicator_circle_black)
                        img1.setImageResource(R.drawable.indicator_circle)
                        img2.setImageResource(R.drawable.indicator_circle)
                    }
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

    }


}
