package com.bcebhagalpur.welcomeslider.student.dashboard.activity

import android.annotation.SuppressLint
import android.content.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.activity.ChatExploreActivity
import com.bcebhagalpur.welcomeslider.activity.LoginActivity
import com.bcebhagalpur.welcomeslider.activity.VidyayanChatingActivity
import com.bcebhagalpur.welcomeslider.bodyfragment.*
import com.bcebhagalpur.welcomeslider.student.dashboard.fragment.ExploreFragment
import com.bcebhagalpur.welcomeslider.student.dashboard.fragment.NotificationFragment
import com.bcebhagalpur.welcomeslider.student.navigationDrawer.activity.BookmarkActivity
import com.bcebhagalpur.welcomeslider.student.navigationDrawer.activity.MyCoursesActivity
import com.bcebhagalpur.welcomeslider.student.navigationDrawer.activity.MyTutorActivity
import com.bcebhagalpur.welcomeslider.student.navigationDrawer.activity.StudentProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import java.util.HashMap


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView
    private lateinit var contentView: LinearLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var exploreFragment: ExploreFragment
    private lateinit var studyFragment: StudyFragment
    private lateinit var teacherFragment: TeacherFragment
    private lateinit var notificationFragment: NotificationFragment
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
        window.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_home)
        changeColor()
        val intent=Intent()
        val extras=intent.getStringExtra("NotificationKey")
        if (extras!=null && extras == "Notification"){
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayout,
                NotificationFragment())
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        menuIcon = findViewById(R.id.menu_icon)
        contentView = findViewById(R.id.content)
        toolbar=findViewById(R.id.toolBar)
        setUpToolbar()

         navigationDrawer()
         drawerHeaderItemHandle()
      /*  val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { Task ->
                if (Task.isSuccessful) {
                    val token = Task.result.token
                    val userId = FirebaseAuth.getInstance().currentUser!!.uid
                    val ref = FirebaseDatabase.getInstance().reference.child("Tokens").child(userId)
                    val tokenHashMap = HashMap<String, Any>()
                    tokenHashMap["uid"] = userId
                    tokenHashMap["token"] = token
                    ref.setValue(tokenHashMap).addOnCompleteListener { Task ->
                        if (Task.isSuccessful) {
                            Log.d(ContentValues.TAG, "Refreshed token: $token ");
                        } else {
                            Log.d(ContentValues.TAG, "Refreshed token: ");
                        }

                    }
                } else {
                    val token = " "
                    val userId = FirebaseAuth.getInstance().currentUser!!.uid
                    val ref = FirebaseDatabase.getInstance().reference.child("Tokens")
                    val tokenHashMap = HashMap<String, Any>()
                    tokenHashMap["uid"] = userId
                    tokenHashMap["token"] = token
                    ref.setValue(tokenHashMap).addOnCompleteListener { Task ->
                        if (Task.isSuccessful) {
                            Log.d(ContentValues.TAG, "Refreshed token: $token ");
                        } else {
                            Log.d(ContentValues.TAG, "Refreshed token: ");
                        }

                    }
                }
            }
        }*/
        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        exploreFragment()
        bottom()
        floatingActionButton.setOnClickListener {
            onAddButtonClicked()
        }
        floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, VidyayanChatingActivity::class.java))
        }
        floatingActionButton3.setOnClickListener {
            startActivity(Intent(this, ChatExploreActivity::class.java))
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
                R.id.explore -> {
                    exploreFragment()
                    supportActionBar!!.title = "Explore Teachers"

                }
                R.id.study -> {
                    studyFragment = StudyFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        studyFragment

                    )
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                    supportActionBar?.hide()
                }

                R.id.notification -> {
                    notificationFragment = NotificationFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        notificationFragment
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
        val endScale = 0.7f
        drawerLayout.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val diffScaledOffset: Float = slideOffset * (1 - endScale)
                val offsetScale = 1 - diffScaledOffset
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
                else-> {
                    val a = Intent(Intent.ACTION_MAIN)
                    a.addCategory(Intent.CATEGORY_HOME)
                    a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(a)
                }
            }
        }
    }

    private fun drawerHeaderItemHandle(){
        val headerView=navigationView.getHeaderView(0)
        val rl= headerView.findViewById<RelativeLayout>(R.id.rl_)
        val r= rl.findViewById<ImageView>(R.id.imgProfileHeader)
        val rname=rl.findViewById<TextView>(R.id.txt_studentname)
        val rl1=headerView.findViewById<RelativeLayout>(R.id.rl_one)
        val rl2=headerView.findViewById<RelativeLayout>(R.id.rl_two)
        val rl3=headerView.findViewById<RelativeLayout>(R.id.rl_three)
        val rl4=headerView.findViewById<RelativeLayout>(R.id.rl_four)
        val rl5=headerView.findViewById<RelativeLayout>(R.id.rl_five)
        val rl6=headerView.findViewById<RelativeLayout>(R.id.rl_six)
        val rl7=headerView.findViewById<RelativeLayout>(R.id.rl_seven)
        val logOut=headerView.findViewById<TextView>(R.id.log_out)
       val userId=FirebaseAuth.getInstance().currentUser
       val userReference=FirebaseDatabase.getInstance().reference.child("STUDENT").child(userId!!.uid)
        userReference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    val pic=p0.child("student pic").value.toString()
                    val name=p0.child("studentName").value.toString()
                    Picasso.get().load(pic).placeholder(R.drawable.logo_transparant).into(r)
                    rname.setText(name)
                    

                }

            }

        })

        rl.setOnClickListener {

            startActivity(Intent(this, StudentProfileActivity::class.java))
        }
        rl3.setOnClickListener {
            startActivity(Intent(this, MyCoursesActivity::class.java))
        }
        rl4.setOnClickListener {
            startActivity(Intent(this, BookmarkActivity::class.java))
        }
        rl5.setOnClickListener {
            startActivity(Intent(this, MyTutorActivity::class.java))
        }
        logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    private fun changeColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.darkerGray)
        }

    }
    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title="Explore Teachers"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver,
            IntentFilter("MyData")
        )
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
    }

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            val title:String?=intent.extras!!.getString("title")//setting values to the TextViews
            val msg=intent.extras!!.getDouble("msg")
            val  mDatabase = FirebaseDatabase.getInstance()
            val mDatabaseReference = mDatabase.reference.child("Notification")
            val mAuth= FirebaseAuth.getInstance()
            val userId= mAuth.currentUser!!.uid

            val currentUserDb = mDatabaseReference.child(userId!!)

            currentUserDb.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val anotherChild = currentUserDb.child(userId)
                    anotherChild.child("userId").setValue(userId)
                    //    anotherChild.child("value1").setValue(value1!!.toString())
                    //   anotherChild.child("value2").setValue(value2!!.toString())
                    anotherChild.child("title").setValue(title!!.toString())
                    anotherChild.child("msg").setValue(msg!!.toString())

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

        }
    }
}
