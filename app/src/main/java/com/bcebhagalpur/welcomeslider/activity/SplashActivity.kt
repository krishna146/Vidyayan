package com.bcebhagalpur.welcomeslider.activity

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.model.MyFirebaseInstanceIDService
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.bcebhagalpur.welcomeslider.teacher.dashboard.activity.HomeTeacher
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import java.util.*

class SplashActivity : AppCompatActivity() {
   private lateinit var progressBar:ProgressBar
 //  lateinit var fcm:MyFirebaseInstanceIDService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_splash)
     //  fcm= MyFirebaseInstanceIDService()
     FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
         Task->
         if (Task.isSuccessful){
             val token=Task.result.token
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
         }else{
             val token=" "
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
        progressBar=findViewById(R.id.progress_bar)
        progressBar.visibility=View.GONE
    }

    override fun onStart() {
        status()
        super.onStart()
    }
    private fun status() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            progressBar.visibility=View.VISIBLE
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val rootRef = FirebaseDatabase.getInstance().reference
            val uidRef = rootRef.child("USERS").child(uid)
            val eventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        progressBar.visibility=View.GONE
                          val userType=dataSnapshot.child("userType").value.toString()
                        if (userType=="Student"){
                            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else if (userType=="Teacher"){
                            val intent =
                                Intent(this@SplashActivity, HomeTeacher::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@SplashActivity,"Some error occurred try again later",Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        val intent =
                            Intent(this@SplashActivity, TeacherStudentActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            }

            uidRef.addListenerForSingleValueEvent(eventListener)
        }else{
            Handler().postDelayed({
                startActivity(
                    Intent(
                        this@SplashActivity,
                        MainActivity::class.java
                    )
                )
            }, 500)
        }
    }

    override fun onPause() {
        finish()
        super.onPause()
    }

}