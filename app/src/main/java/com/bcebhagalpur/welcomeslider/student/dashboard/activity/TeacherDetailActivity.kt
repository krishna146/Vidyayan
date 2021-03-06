@file:Suppress("DEPRECATION")

package com.bcebhagalpur.welcomeslider.student.dashboard.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.bcebhagalpur.welcomeslider.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class TeacherDetailActivity : AppCompatActivity() {

    private lateinit var imgTeacher:ImageView
    private lateinit var txtTeacherName:TextView
    private lateinit var btnTeacherButton: Button
    private lateinit var txtQualification:TextView
    private lateinit var txtCollegeName:TextView
    private lateinit var txtCity:TextView
    private lateinit var txtAddress:TextView
    private lateinit var txtClass:TextView
    private lateinit var txtTiming:TextView
    private lateinit var txtMode:TextView
    private lateinit var txtSubject:TextView
    private lateinit var txtLanguage:TextView
    private lateinit var txtPrice:TextView
    private lateinit var scrollView:ScrollView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_detail)

        imgTeacher=findViewById(R.id.imgTeacher)
        txtTeacherName=findViewById(R.id.txtTeacherName)
        btnTeacherButton=findViewById(R.id.btnSendRequest)
        txtQualification=findViewById(R.id.txtQualification)
        txtCollegeName=findViewById(R.id.txtCollegeName)
        txtCity=findViewById(R.id.txtCity)
        txtAddress=findViewById(R.id.txtAddress)
        txtClass=findViewById(R.id.txtClass)
        txtTiming=findViewById(R.id.txtTiming)
        txtMode=findViewById(R.id.txtMode)
        txtSubject=findViewById(R.id.txtSubject)
        txtLanguage=findViewById(R.id.txtLangauge)
        txtPrice=findViewById(R.id.txtPrice)
        scrollView=findViewById(R.id.scrollTop)

        val teacherClass=intent.getStringExtra("teacherClass")
        val college=intent.getStringExtra("college")
        val language=intent.getStringExtra("language")
        val mobileNumber=intent.getStringExtra("mobileNumber")
        val mode=intent.getStringExtra("mode")
        val price=intent.getStringExtra("price")
        val qualification=intent.getStringExtra("qualification")
        val status=intent.getStringExtra("status")
        val subject=intent.getStringExtra("subject")
        val teacherAddress=intent.getStringExtra("teacherAddress")
        val teacherCity=intent.getStringExtra("teacherCity")
        val teacherDob=intent.getStringExtra("teacherDob")
        val teacherEmail=intent.getStringExtra("teacherEmail")
        val teacherGender=intent.getStringExtra("teacherGender")
        val teacherName=intent.getStringExtra("teacherName")
        val timing=intent.getStringExtra("timing")
        val userIdTeacher=intent.getStringExtra("userId")
        val teacherImage=intent.getStringExtra("teacherImage")

        txtTeacherName.text=teacherName
        txtCollegeName.text=college
        btnTeacherButton=findViewById(R.id.btnSendRequest)
        txtQualification.text=qualification
        txtCity.text=teacherCity
        txtAddress.text=teacherAddress
        txtClass.text=teacherClass
        txtTiming.text=timing
        txtMode.text=mode
        txtSubject.text=subject
        txtLanguage.text=language
        txtPrice.text = "₹ $price"
        Picasso.get().load(teacherImage).error(R.drawable.logo_transparant).into(imgTeacher)
        btnTeacherButton.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("VIDYAYAN")
            progressDialog.setMessage("We are checking your status")
            progressDialog.show()
            val userId= FirebaseAuth.getInstance().currentUser!!.uid
            val userNumber= FirebaseAuth.getInstance().currentUser!!.phoneNumber
            val request = FirebaseDatabase.getInstance().reference.child("Request")
            val anotherChild=request.child(userIdTeacher!!).child(userId)
            anotherChild.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    progressDialog.hide()
                    if (!snapshot.exists()) {
                        val student =
                            FirebaseDatabase.getInstance().reference.child("STUDENT")
                        val anotherChildStudent = student.child(userId)
                        anotherChildStudent.addListenerForSingleValueEvent(object :
                            ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    val studentCity = snapshot.child("studentCity").value.toString()
                                    val studentClass = snapshot.child("studentClass").value.toString()
                                    val studentName = snapshot.child("studentName").value.toString()
                                    request.addListenerForSingleValueEvent(object :
                                        ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val anotherChild2=request.child(userIdTeacher)
                                            val anotherChild1=anotherChild2.child(userId)
                                            anotherChild1.child("studentCity").setValue(studentCity)
                                            anotherChild1.child("studentClass").setValue(studentClass)
                                            anotherChild1.child("studentName").setValue(studentName)
                                            anotherChild1.child("studentNumber").setValue(userNumber)
                                            anotherChild1.child("studentId").setValue(userId)
                                            anotherChild1.child("teacherName").setValue(teacherName)
                                            anotherChild1.child("teacherNumber").setValue(mobileNumber)
                                            anotherChild1.child("teacherId").setValue(userIdTeacher)
                                            anotherChild1.child("teacherCity").setValue(teacherCity)
                                            Snackbar.make(scrollView,"Request sent successfully...", Snackbar.LENGTH_LONG).show()
                                        }

                                        override fun onCancelled(error: DatabaseError) {

                                        }

                                    })
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {

                            }

                        })
                    }else{
                        Snackbar.make(scrollView,"Request already sent...", Snackbar.LENGTH_LONG).show()
                    }
                }override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }
}