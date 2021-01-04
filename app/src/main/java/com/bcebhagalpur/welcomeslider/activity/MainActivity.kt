package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.PagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
//    private lateinit var btnSkip:Button
//    private lateinit var btnNext:Button
    private lateinit var img1:ImageView
    private lateinit var img2:ImageView
    private lateinit var img3:ImageView
    private lateinit var adapter:PagerAdapter
    private lateinit var btnStart:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager=findViewById(R.id.viewPager)
//        btnSkip=findViewById(R.id.btnSkip)
//        btnNext=findViewById(R.id.btnNext)
        btnStart=findViewById(R.id.btnStart)
        img1=findViewById(R.id.img1)
        img2=findViewById(R.id.img2)
        img3=findViewById(R.id.img3)

        adapter= PagerAdapter(supportFragmentManager)
        viewPager.adapter=adapter
//
//        btnNext.setOnClickListener {
//            viewPager.currentItem++
//        }
//        btnSkip.setOnClickListener {
//            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
//            finish()
//        }

        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
            {

            }

            override fun onPageSelected(position: Int) {

                if (position==2){
                    btnStart.visibility=View.VISIBLE
                    btnStart.setOnClickListener {
                        startActivity(Intent(this@MainActivity,HomeActivity::class.java))
                        finish()
                    }
                }else{
                    btnStart.visibility=View.GONE
                }

//                if (position==2){
//                    btnSkip.visibility=View.GONE
//                }else{
//                    btnSkip.visibility=View.VISIBLE
//                }
//                if (position==2){
//                    btnNext.text=getString(R.string.get_started)
//                    btnSkip.visibility=View.GONE
//                    btnNext.setOnClickListener {
//                        startActivity(Intent(this@MainActivity,HomeActivity::class.java))
//                        finish()
//                    }
//                }
//                else{
//                    btnNext.text=getString(R.string.next)
//                    btnNext.setOnClickListener {
//                        viewPager.currentItem++
//                    }
//                }

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

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }else{
            showSystemUI()
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}
