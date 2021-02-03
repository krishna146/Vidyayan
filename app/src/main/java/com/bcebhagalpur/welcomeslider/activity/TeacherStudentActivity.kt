package com.bcebhagalpur.welcomeslider.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.teacher.dashboard.activity.HomeTeacher
import com.bcebhagalpur.welcomeslider.teacher.starter.activity.TeacherRegistrationActivity2
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import kotlinx.android.synthetic.main.activity_teacher_student.*
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class TeacherStudentActivity : AppCompatActivity() {

    private lateinit var btnStudent: ImageButton
    private lateinit var btnTeacher: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_teacher_student)

        btnStudent = findViewById(R.id.btnstudent)
        btnTeacher = findViewById(R.id.btnteacher)
//        val myUserStateObserver=Observer<FirebaseAuthUserState>{userState->
//            when(userState){
//                is UserSignedOut->{
//
//                }
//                is UserSignedIn->{
//
//                }
//                is UserUnknown->{
//
//                }
//            }
//        }
//
//        val authStateLiveData=


        btnStudent.setOnClickListener {
            val number = intent.getStringExtra("mobileNumber")
            val intent = Intent(this@TeacherStudentActivity, ChooseClassActivity::class.java)
            intent.putExtra("userType", "Student")
            intent.putExtra("mobileNumber", number)
            startActivity(intent)
        }

        btnTeacher.setOnClickListener {
            val number = intent.getStringExtra("mobileNumber")
            val intent = Intent(this@TeacherStudentActivity, TeacherRegistrationActivity2::class.java)
            intent.putExtra("mobileNumber", number)
            intent.putExtra("userType", "Teacher")
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

//        else {
//                    val uid = FirebaseAuth.getInstance().currentUser!!.uid
//                    val rootRef = FirebaseDatabase.getInstance().reference
//                    val uidRef = rootRef.child("CHECKSTUDENT").child(uid)
//                    val eventListener: ValueEventListener = object : ValueEventListener {
//                        override fun onDataChange(dataSnapshot: DataSnapshot) {
//                            if (dataSnapshot.exists()) {
//                                val intent =
//                                    Intent(this@TeacherStudentActivity, OtpVerifyActivity::class.java)
//                                intent.putExtra("userType", "Student")
//                                startActivity(intent)
//                                finish()
//                            } else {
//                                val intent =
//                                    Intent(this@TeacherStudentActivity, OtpVerifyActivity::class.java)
//                                intent.putExtra("userType", "Teacher")
//                                startActivity(intent)
//                                finish()
//                            }
//                        }
//
//                        override fun onCancelled(databaseError: DatabaseError) {}
//                    }
//
//                    uidRef.addListenerForSingleValueEvent(eventListener)
//                }
            }

//    sealed class FirebaseAuthUserState
//    data class UserSignedIn(val user: FirebaseUser) : FirebaseAuthUserState()
//    object UserSignedOut : FirebaseAuthUserState()
//    object UserUnknown : FirebaseAuthUserState()
//    @MainThread
//    fun FirebaseAuth.newFirebaseAuthStateLiveData(
//        context: CoroutineContext = EmptyCoroutineContext
//    ): LiveData<FirebaseAuthUserState> {
//        val ld = FirebaseAuthStateLiveData(this)
//        return iveData(context) {
//            emitSource(ld)
//        }
//    }
//
//    private fun emitSource(ld: FirebaseAuthStateLiveData) {
//
//    }
//
//    private fun iveData(
//        context: CoroutineContext,
//        function: () -> Unit
//    ): LiveData<FirebaseAuthUserState> {
//
//    }
//
//    class FirebaseAuthStateLiveData(private val auth: FirebaseAuth) : LiveData<FirebaseAuthUserState>() {
//        private val authStateListener = MyAuthStateListener()
//        init {
//            value = UserUnknown
//        }
//        override fun onActive() {
//            auth.addAuthStateListener(authStateListener)
//        }
//        override fun onInactive() {
//            auth.removeAuthStateListener(authStateListener)
//        }
//        private inner class MyAuthStateListener : FirebaseAuth.AuthStateListener {
//            override fun onAuthStateChanged(auth: FirebaseAuth) {
//                val user = auth.currentUser
//                value = if (user != null) {
//                    UserSignedIn(user)
//                }
//                else {
//                    UserSignedOut
//                }
//            }
//        }
//    }
//



