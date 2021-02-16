package com.bcebhagalpur.welcomeslider.student.dashboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.textclassifier.TextLanguage
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bcebhagalpur.welcomeslider.R
import com.squareup.picasso.Picasso

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

        intent.getStringExtra("teacherClass")
        intent.getStringExtra("college")
        intent.getStringExtra("language")
        intent.getStringExtra("mobileNumber")
        intent.getStringExtra("mode")
        intent.getStringExtra("price")
        intent.getStringExtra("qualification")
        intent.getStringExtra("status")
        intent.getStringExtra("subject")
        intent.getStringExtra("teacherAddress")
        intent.getStringExtra("teacherCity")
        intent.getStringExtra("teacherDob")
        intent.getStringExtra("teacherEmail")
        intent.getStringExtra("teacherGender")
        intent.getStringExtra("teacherName")
        intent.getStringExtra("timing")
        intent.getStringExtra("userId")

    }
}