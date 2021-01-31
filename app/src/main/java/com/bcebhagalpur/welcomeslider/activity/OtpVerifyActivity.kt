package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.teacher.dashboard.activity.HomeTeacher
import com.bcebhagalpur.welcomeslider.teacher.starter.activity.TeacherRegistrationActivity2
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_otp_verify.*
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class OtpVerifyActivity : AppCompatActivity() {
    private lateinit var btnVerify:Button
    private lateinit var txtNumber:TextView
    private lateinit var txtResend:TextView
    private lateinit var etOtp:EditText

    private var mVerificationId: String? = null
    private lateinit var mAuth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)


        val number = intent.getStringExtra("mobileNumber")!!.toString()
//        val userType=intent.getStringExtra("studentOrTeacher")!!.toString()


        btnVerify=findViewById(R.id.btnVerify)
        txtNumber=findViewById(R.id.txtNumber)
        txtResend=findViewById(R.id.txtResend)
        etOtp=findViewById(R.id.etOtp)
        txtNumber.text = "+91 $number"
        txtNumber.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }

        mAuth = FirebaseAuth.getInstance()
        sendVerificationCode(number)

        btnVerify.setOnClickListener(){




                val code: String = etOtp.getText().toString().trim()
               if(code.length==6) {
                   verifyVerificationCode(code)
               }else{
                Toast.makeText(this,"Enter Otp",Toast.LENGTH_SHORT).show()}


        }
    }

    private val mCallbacks: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                    etOtp.setText(code)
                    verifyVerificationCode(code)

                }
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@OtpVerifyActivity, e.message, Toast.LENGTH_LONG).show()
            }
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                mVerificationId = s
            }
        }






    private fun sendVerificationCode(mobile: String) {
        @Suppress("DEPRECATION")
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$mobile",
            60,
            TimeUnit.SECONDS,
            this,
            mCallbacks
        )
    }

    private fun verifyVerificationCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val userType=intent.getStringExtra("studentOrTeacher")!!.toString()
        val number = intent.getStringExtra("mobileNumber")!!.toString()
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this@OtpVerifyActivity) { task ->
                if (task.isSuccessful) {
                    if (userType=="Student"){
                        val uid = FirebaseAuth.getInstance().currentUser!!.uid
                        val rootRef = FirebaseDatabase.getInstance().reference
                        val uidRef = rootRef.child("CheckStudent").child(uid)
                        val eventListener: ValueEventListener = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    startActivity(Intent(this@OtpVerifyActivity,HomeActivity::class.java))
                                }else{
                                    val intent=Intent(this@OtpVerifyActivity,TeacherStudentActivity::class.java)
                                    intent.putExtra("mobileNumber",number)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {}
                        }
                        uidRef.addListenerForSingleValueEvent(eventListener)
                    }else if (userType=="Teacher"){
                        val uid = FirebaseAuth.getInstance().currentUser!!.uid
                        val rootRef = FirebaseDatabase.getInstance().reference
                        val uidRef = rootRef.child("CheckTeacher").child(uid)
                        val eventListener: ValueEventListener = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    startActivity(Intent(this@OtpVerifyActivity,HomeTeacher::class.java))
                                }else{
                                    val intent=Intent(this@OtpVerifyActivity,TeacherRegistrationActivity2::class.java)
                                    intent.putExtra("mobileNumber",number)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {}
                        }
                        uidRef.addListenerForSingleValueEvent(eventListener)
                    }

//                    val isNewUser = task.result.additionalUserInfo!!.isNewUser
//                    if (isNewUser) {
//
//                    } else {
//                        val intent = Intent(this@OtpVerifyActivity, HomeActivity::class.java)
//                        intent.flags =
//                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        startActivity(intent)
//                        finish()
//                    }
                }
                else {
                    var message = "Something is wrong, we will fix it soon..."
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered..."
                    }
                }
            }
    }

//    override fun onStart() {
//        super.onStart()
//        val user = FirebaseAuth.getInstance().currentUser
//        if (user != null) {
//            startActivity( Intent(this, HomeActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                finish()
//            })
//        }
//    }

}