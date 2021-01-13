package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.bodyfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_teacher.*

class HomeTeacher : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var exploreFragment: ExploreTeacherFragment
    private lateinit var moreFragment: MoreTeacherFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var studentFragment: StudentFragment
    private var previousMenuItem: MenuItem? = null
    private val rotateOpen: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_open_anims)}
    private val rotateClose: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.rotate_close_anims)}
    private val fromButton: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.from_button_anims)}
    private val toButton: Animation by lazy{ AnimationUtils.loadAnimation(this,R.anim.to_button_anims)}
    private var clicked=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // window.requestFeature(Window.FEATURE_NO_TITLE)
       // window.setFlags(
         //   WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
           // WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        //)
        setContentView(R.layout.activity_home_teacher)
        bottomNavigationView=findViewById(R.id.bottomNavigationView2)
        bottom()
        floatingActionButton7.setOnClickListener {
            onAddButtonClicked()
        }
        floatingActionButton8.setOnClickListener {
            Toast.makeText(this,"hy", Toast.LENGTH_SHORT).show()
        }
        floatingActionButton9.setOnClickListener {
            Toast.makeText(this,"by", Toast.LENGTH_SHORT).show()
        }
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
                R.id.exploreteacher->{
                    exploreFragmentTeacher()
//                    draw(6)
                }
                R.id.searchteacher->{
                    searchFragment=SearchFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayoutteacher,searchFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
//                    draw(2)
                }
                R.id.studentteacher->{
                    studentFragment= StudentFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayoutteacher,studentFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
//                    draw()
                }

                R.id.moreteacher->{
                    moreFragment= MoreTeacherFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayoutteacher,moreFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
                }
            }
            true
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun exploreFragmentTeacher(){
        exploreFragment= ExploreTeacherFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutteacher, exploreFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        supportActionBar?.show()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id!= android.R.id.home){
            exploreFragmentTeacher()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayoutteacher)){
            !is ExploreTeacherFragment-> {
                exploreFragmentTeacher()
            }
            else->super.onBackPressed()
        }
    }




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
            floatingActionButton8.visibility= View.VISIBLE
            floatingActionButton9.visibility= View.VISIBLE
        }else{
            floatingActionButton8.visibility= View.INVISIBLE
            floatingActionButton9.visibility= View.INVISIBLE
        }
    }
    private fun setAnimation(clicked: Boolean)
    {
        if(!clicked)
        {
            floatingActionButton8.startAnimation(fromButton)
            floatingActionButton9.startAnimation(fromButton)
            floatingActionButton7.startAnimation(rotateOpen)
        }else{
            floatingActionButton8.startAnimation(toButton)
            floatingActionButton9.startAnimation(toButton)
            floatingActionButton7.startAnimation(rotateClose)
        }
    }
    private fun setClickable(clicked: Boolean)
    {
        if(!clicked){
            floatingActionButton8.isClickable=true
            floatingActionButton9.isClickable=true
        }else{
            floatingActionButton8.isClickable=false
            floatingActionButton9.isClickable=false
        }
    }

}