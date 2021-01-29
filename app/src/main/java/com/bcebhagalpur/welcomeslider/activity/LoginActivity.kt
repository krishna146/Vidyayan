package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.starter.activity.RegistrationActivity
import com.bcebhagalpur.welcomeslider.teacher.starter.activity.TeacherRegistrationActivity2
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val ss:String = intent.getStringExtra("Designation").toString()
        setContentView(R.layout.activity_login)
        btn_get_otp.setOnClickListener(){
            val intent = Intent(this, OtpVerifyActivity::class.java)
            intent.putExtra("Number", "${etMobileNumber.editText?.text}")
            intent.putExtra("Designation",ss)
            startActivity(intent)
        }
    }
}
