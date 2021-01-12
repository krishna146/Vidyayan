package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.bodyfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var exploreFragment: ExploreFragment
    private lateinit var moreFragment: MoreFragment
    private lateinit var searchFragment:SearchFragment
    private lateinit var studentFragment: StudentFragment
    private lateinit var teacherFragment: TeacherFragment
    private var previousMenuItem: MenuItem? = null
    private val rotateOpen: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_open_anims)}
    private val rotateClose: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_close_anims)}
    private val fromButton: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.from_button_anims)}
    private val toButton: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.to_button_anims)}
    private var clicked=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView=findViewById(R.id.bottomNavigationView)

        bottom()
        floatingActionButton.setOnClickListener {
            onAddButtonClicked()
        }
        floatingActionButton2.setOnClickListener {
            Toast.makeText(this,"hy",Toast.LENGTH_SHORT).show()
        }
        floatingActionButton3.setOnClickListener {
            Toast.makeText(this,"by",Toast.LENGTH_SHORT).show()
        }
        exploreFragment()
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
        supportActionBar?.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id!= android.R.id.home){
            exploreFragment()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
            !is ExploreFragment-> {
                exploreFragment()
            }
            else->super.onBackPressed()
        }
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
private fun onAddButtonClicked()
{
    setVisibility(clicked)
    setAnimation(clicked)
    setClickable(clicked)
    clicked = !clicked

}
    private fun setVisibility(clicked:Boolean)
    {
        if(!clicked)
        {
            floatingActionButton2.visibility= View.VISIBLE
            floatingActionButton3.visibility= View.VISIBLE
        }else{
            floatingActionButton2.visibility= View.INVISIBLE
            floatingActionButton3.visibility= View.INVISIBLE
        }
    }
    private fun setAnimation(clicked: Boolean)
    {
        if(!clicked)
        {
            floatingActionButton2.startAnimation(fromButton)
            floatingActionButton3.startAnimation(fromButton)
            floatingActionButton.startAnimation(rotateOpen)
        }else{
            floatingActionButton2.startAnimation(toButton)
            floatingActionButton3.startAnimation(toButton)
            floatingActionButton.startAnimation(rotateClose)
        }
    }
    private fun setClickable(clicked: Boolean)
    {
        if(!clicked){
            floatingActionButton2.isClickable=true
            floatingActionButton3.isClickable=true
        }else{
            floatingActionButton2.isClickable=false
            floatingActionButton3.isClickable=false
        }
    }


}