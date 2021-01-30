package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.teacher.starter.activity.TeacherRegistrationActivity2
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import kotlinx.android.synthetic.main.activity_teacher_student.*
import java.text.SimpleDateFormat
import java.util.*


class TeacherStudentActivity : AppCompatActivity() {
    private lateinit var btnStudent:ImageButton
    private lateinit var btnTeacher:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_teacher_student)

        btnStudent=findViewById(R.id.btnstudent)
        btnTeacher=findViewById(R.id.btnteacher)

        btnStudent.setOnClickListener {
           val intent = Intent(this@TeacherStudentActivity, LoginActivity::class.java)
            intent.putExtra("student", "Student");
            startActivity(intent)
        }
        btnTeacher.setOnClickListener() {
            val intent = Intent(this@TeacherStudentActivity, LoginActivity::class.java)
            intent.putExtra("student", "Teacher");
            startActivity(intent)
        }

    }
}
