package com.bcebhagalpur.welcomeslider.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import kotlinx.android.synthetic.main.activity_teacher_student.*
import java.util.*

class TeacherStudentActivity : AppCompatActivity() {
    private lateinit var btnStudent:ImageButton
    private lateinit var btnTeacher:ImageButton

    private val sharedPrefFile = "userTypeSharedPreferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_teacher_student)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)

        btnStudent=findViewById(R.id.btnstudent)
        btnTeacher=findViewById(R.id.btnteacher)

        btnStudent.setOnClickListener {

            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("student","Student")
            editor.apply()
            editor.apply()
            val intent = Intent(this@TeacherStudentActivity, LoginActivity::class.java)
            intent.putExtra("student", "Student")
            startActivity(intent)
        }

        btnTeacher.setOnClickListener {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("student","Student")
            editor.apply()
            editor.commit()
            val intent = Intent(this@TeacherStudentActivity, LoginActivity::class.java)
            intent.putExtra("student", "Teacher")
            startActivity(intent)
        }

    }
}

