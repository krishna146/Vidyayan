@file:Suppress("DEPRECATION")

package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.bcebhagalpur.welcomeslider.teacher.dashboard.activity.HomeTeacher
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_otp_verify.*
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

        val teacherNumber=intent.getStringExtra("mobileNumber")

        btnVerify=findViewById(R.id.btnVerify)
        txtNumber=findViewById(R.id.txtNumber)
        txtResend=findViewById(R.id.txtResend)
        etOtp=findViewById(R.id.etOtp)
        txtNumber.text = "+91 $teacherNumber"
        txtNumber.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        mAuth = FirebaseAuth.getInstance()
        sendVerificationCode(teacherNumber!!)

        btnVerify.setOnClickListener {
            val code: String = etOtp.text.toString().trim()

            verifyVerificationCode(code)

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
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("VIDYAYAN")
        progressDialog.setMessage("We are processing, please wait")
        progressDialog.show()
        progressDialog.setCancelable(false)
        val number = intent.getStringExtra("mobileNumber")!!.toString()
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this@OtpVerifyActivity) { task ->
                if (task.isSuccessful) {
                    val isNewUser = task.result.additionalUserInfo!!.isNewUser
                    if (!isNewUser){
                       status(progressDialog)
                    }
                    else{
                        progressDialog.hide()
                     val intent=Intent(this,TeacherStudentActivity::class.java)
                        intent.putExtra("mobileNumber",number)
                     startActivity(intent)
                     finish()
                    }

                }
                else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        progressDialog.hide()
                        val message = "Invalid code entered..."
                        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun status(progress:ProgressDialog) {
        val number = intent.getStringExtra("mobileNumber")!!.toString()
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val rootRef = FirebaseDatabase.getInstance().reference
            val uidRef = rootRef.child("USERS").child(uid)
            val eventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        when (dataSnapshot.child("userType").value.toString()) {
                            "Student" -> {
                                progress.hide()
                                val intent = Intent(this@OtpVerifyActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            "Teacher" -> {
                                progress.hide()
                                val intent =
                                    Intent(this@OtpVerifyActivity, HomeTeacher::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else -> {
                               progress.hide()
                                Toast.makeText(this@OtpVerifyActivity,"Some error occurred try again later",Toast.LENGTH_SHORT).show()
                            }
                        }

                    } else {
                        progress.hide()
                        val intent =
                            Intent(this@OtpVerifyActivity, TeacherStudentActivity::class.java)
                        intent.putExtra("mobileNumber",number)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            }

            uidRef.addListenerForSingleValueEvent(eventListener)
    }

}