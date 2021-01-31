package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*


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
       // val number_s= etMobileNumber.editText!!.text.toString().trim()





        btnNext.setOnClickListener {


            val number= etMobileNumber.editText!!.text.toString().trim()
            if(number.isEmpty()||number.length!=10) {
                Toast.makeText(this,"Enter Valid Number",Toast.LENGTH_SHORT).show()
            }
            else
            {
                btnNext.visibility = View.GONE
                loginProgressBar.visibility = View.VISIBLE
            val userType=intent.getStringExtra("userType")
            val intent=Intent(this, OtpVerifyActivity::class.java)
            intent.putExtra("mobileNumber", number)
            intent.putExtra("studentOrTeacher",userType)
            startActivity(intent)
        }}
    }
}

