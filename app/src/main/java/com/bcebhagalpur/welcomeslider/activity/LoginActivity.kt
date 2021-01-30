package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.RegistrationActivity
import com.bcebhagalpur.welcomeslider.teacher.starter.activity.TeacherRegistrationActivity2
import com.firebase.ui.auth.ui.phone.PhoneVerificationActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

   private lateinit var etMobileNumber:TextInputLayout
    private lateinit var btnNext:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_login)

        etMobileNumber=findViewById(R.id.etMobileNumber)
        btnNext=findViewById(R.id.btn_get_otp)
        btnNext.setOnClickListener {

            btnNext.visibility = View.GONE
            loginProgressBar.visibility = View.VISIBLE
            val number= etMobileNumber.editText!!.text.toString().trim()
            val studentOrTeacher=intent.getStringExtra("student")
            val intent=Intent(this,OtpVerifyActivity::class.java)
            intent.putExtra("mobileNumber",number)
            intent.putExtra("studentOrTeacher",studentOrTeacher)
            startActivity(intent)
        }
    }
}

