package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.teacher.dashboard.activity.HomeTeacher
import com.bcebhagalpur.welcomeslider.teacher.starter.activity.TeacherRegistrationActivity2
import kotlinx.android.synthetic.main.activity_otp_verify.*

class OtpVerifyActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var number: String = intent.getStringExtra("Number").toString()
        var designation = intent.getStringExtra("Designation").toString()
        setContentView(R.layout.activity_otp_verify)
        txtNumber.text = "+91 $number"


        btnVerify.setOnClickListener(){

            if(etOtp.text.toString()== "1234" && designation =="student"){

                startActivity(Intent(this,HomeActivity::class.java))

            }
            if(etOtp.text.toString()!="1234" && designation == "student" ){
                startActivity(Intent(this, ChooseClassActivity::class.java))
            }
            if(etOtp.text.toString()== "1234" && designation =="teacher"){

                startActivity(Intent(this,HomeTeacher::class.java))

            }
            if(etOtp.text.toString()!="1234" && designation == "teacher" ){
                startActivity(Intent(this, TeacherRegistrationActivity2::class.java))
            }

        }
    }
}