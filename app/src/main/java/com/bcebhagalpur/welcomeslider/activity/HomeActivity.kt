package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.bodyfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var exploreFragment: ExploreFragment
    private lateinit var moreFragment: MoreFragment
    private lateinit var searchFragment:SearchFragment
    private lateinit var studentFragment: StudentFragment
    private lateinit var teacherFragment: TeacherFragment
    private var previousMenuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.requestFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_home)

        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        exploreFragment()
        bottom()
    }

    private fun bottom(){
        bottomNavigationView.setOnNavigationItemSelectedListener {

            if (previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId){
                R.id.explore->{
                    exploreFragment()
//                    draw(6)
                }
                R.id.search->{
                    searchFragment=SearchFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,searchFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
//                    draw(2)
                }
                R.id.student->{
                    studentFragment= StudentFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,studentFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
//                    draw()
                }
                R.id.teacher->{
                    teacherFragment= TeacherFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,teacherFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
//                    draw()
                }
                R.id.more->{
                    moreFragment= MoreFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,moreFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
                }
            }
            true
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun exploreFragment(){
        exploreFragment= ExploreFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, exploreFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        supportActionBar?.title ="Play"
        supportActionBar?.show()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//
//        if (id == android.R.id.home){
//
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
            !is ExploreFragment-> {
                exploreFragment()
            }
            else->super.onBackPressed()
        }
    }

    private fun slideUpDownBottomSheet() {

    }

//    private fun draw(i:Int){
//        bottomNavigationView.firstCurveStartPoint.set(bottomNavigationView.navigationBarWidth/i-bottomNavigationView.radius
//        *2-bottomNavigationView.radius/3,0)
//        bottomNavigationView.firstCurveEndPoint.set(bottomNavigationView.navigationBarWidth/i,
//        bottomNavigationView.radius+bottomNavigationView.radius/4)
//
//        bottomNavigationView.firstCurveStartPoint=bottomNavigationView.firstCurveEndPoint
//
//        bottomNavigationView.secondCurveEndPoint.set(bottomNavigationView.navigationBarWidth/i+bottomNavigationView
//            .radius*2+bottomNavigationView.radius/3,0)
//
//        bottomNavigationView.firstCurveControlPoint1.set(bottomNavigationView.firstCurveStartPoint.x+bottomNavigationView
//            .radius+bottomNavigationView.radius/4,bottomNavigationView.firstCurveStartPoint.y)
//
//        bottomNavigationView.firstCurveControlPoint2.set(bottomNavigationView.firstCurveEndPoint.x-bottomNavigationView.radius
//        *2+bottomNavigationView.radius,bottomNavigationView.firstCurveEndPoint.y)
//
//        bottomNavigationView.secondCurveControlPoint1.set(bottomNavigationView.secondCurveStartPoint.x+bottomNavigationView
//            .radius*2-bottomNavigationView.radius,bottomNavigationView.secondCurveStartPoint.y)
//
//        bottomNavigationView.firstCurveControlPoint2.set(bottomNavigationView.secondCurveEndPoint.x-(bottomNavigationView.radius
//                +bottomNavigationView.radius/4),bottomNavigationView.secondCurveEndPoint.y)
//    }
//    private fun draw(){
//        bottomNavigationView.firstCurveStartPoint.set(bottomNavigationView.navigationBarWidth*10/12
//                -bottomNavigationView.radius
//                *2-bottomNavigationView.radius/3,0)
//        bottomNavigationView.firstCurveEndPoint.set(bottomNavigationView.navigationBarWidth*10/12,
//            bottomNavigationView.radius+bottomNavigationView.radius/4)
//
//        bottomNavigationView.firstCurveStartPoint=bottomNavigationView.firstCurveEndPoint
//
//        bottomNavigationView.secondCurveEndPoint.set(bottomNavigationView.navigationBarWidth*10/12+bottomNavigationView
//            .radius*2+bottomNavigationView.radius/3,0)
//
//        bottomNavigationView.firstCurveControlPoint1.set(bottomNavigationView.firstCurveStartPoint.x+bottomNavigationView
//            .radius+bottomNavigationView.radius/4,bottomNavigationView.firstCurveStartPoint.y)
//
//        bottomNavigationView.firstCurveControlPoint2.set(bottomNavigationView.firstCurveEndPoint.x-bottomNavigationView.radius
//                *2+bottomNavigationView.radius,bottomNavigationView.firstCurveEndPoint.y)
//
//        bottomNavigationView.secondCurveControlPoint1.set(bottomNavigationView.secondCurveStartPoint.x+bottomNavigationView
//            .radius*2-bottomNavigationView.radius,bottomNavigationView.secondCurveStartPoint.y)
//
//        bottomNavigationView.firstCurveControlPoint2.set(bottomNavigationView.secondCurveEndPoint.x-(bottomNavigationView.radius
//                +bottomNavigationView.radius/4),bottomNavigationView.secondCurveEndPoint.y)
//    }

}