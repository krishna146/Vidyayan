package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import androidx.fragment.app.FragmentTransaction
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.bodyfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView
    private lateinit var contentView: LinearLayout

    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var exploreFragment: ExploreFragment
//    private lateinit var moreFragment: MoreFragment
    private lateinit var searchFragment:SearchFragment
    private lateinit var studentFragment: StudentFragment
    private lateinit var teacherFragment: TeacherFragment
    private var previousMenuItem: MenuItem? = null
    private val rotateOpen: Animation by lazy{ AnimationUtils.loadAnimation(
        this,
        R.anim.rotate_open_anims
    )}
    private val rotateClose: Animation by lazy{ AnimationUtils.loadAnimation(
        this,
        R.anim.rotate_close_anims
    )}
    private val fromButton: Animation by lazy{ AnimationUtils.loadAnimation(
        this,
        R.anim.from_button_anims
    )}
    private val toButton: Animation by lazy{ AnimationUtils.loadAnimation(
        this,
        R.anim.to_button_anims
    )}
    private var clicked=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.requestFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        menuIcon = findViewById(R.id.menu_icon)
        contentView = findViewById(R.id.content)

         navigationDrawer()

        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        exploreFragment()
        bottom()
        floatingActionButton.setOnClickListener {
            onAddButtonClicked()
        }
        floatingActionButton2.setOnClickListener {
            Toast.makeText(this, "hy", Toast.LENGTH_SHORT).show()
        }
        floatingActionButton3.setOnClickListener {
            Toast.makeText(this, "by", Toast.LENGTH_SHORT).show()
        }
        exploreFragment()
        drawerHeaderItemHandle()

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
                R.id.explore -> {
                    exploreFragment()

                }
                R.id.search -> {
                    searchFragment = SearchFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        searchFragment
                    )
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()

                }

                R.id.teacher -> {
                    teacherFragment = TeacherFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        teacherFragment
                    )
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

private fun onAddButtonClicked()
{
    setVisibility(clicked)
    setAnimation(clicked)
    setClickable(clicked)
    clicked = !clicked

}
    private fun setVisibility(clicked: Boolean)
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

    @SuppressLint("RtlHardcoded")
    private fun navigationDrawer() {

        //Naviagtion Drawer
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener(this)
        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.END)) drawerLayout.closeDrawer(
                GravityCompat.END
            ) else drawerLayout.openDrawer(GravityCompat.END)
        }
        animateNavigationDrawer()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    private fun animateNavigationDrawer() {
        val END_SCALE = 0.7f
        drawerLayout.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val diffScaledOffset: Float = slideOffset * ( 1-END_SCALE)
                val offsetScale = 1- diffScaledOffset
                contentView.scaleX = offsetScale
                contentView.scaleY = offsetScale
                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff = contentView.width * diffScaledOffset / 2
                val xTranslation = xOffsetDiff - xOffset
                contentView.translationX = xTranslation
            }
        })
    }

    @SuppressLint("RtlHardcoded")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END)

        }else{
                        when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
                !is ExploreFragment -> {
                    exploreFragment()
                }
                else->super.onBackPressed()
            }
        }

    }

    private fun drawerHeaderItemHandle(){
        val headerView=navigationView.getHeaderView(0)
        val rl=headerView.findViewById<RelativeLayout>(R.id.rl_)
        val rl1=headerView.findViewById<RelativeLayout>(R.id.rl_one)
        val rl2=headerView.findViewById<RelativeLayout>(R.id.rl_two)
        val rl3=headerView.findViewById<RelativeLayout>(R.id.rl_three)
        val rl4=headerView.findViewById<RelativeLayout>(R.id.rl_four)
        val rl5=headerView.findViewById<RelativeLayout>(R.id.rl_five)
        val rl6=headerView.findViewById<RelativeLayout>(R.id.rl_six)
        val rl7=headerView.findViewById<RelativeLayout>(R.id.rl_seven)

        rl.setOnClickListener {
            startActivity(Intent(this,StudentProfileActivity::class.java))
        }
    }

}