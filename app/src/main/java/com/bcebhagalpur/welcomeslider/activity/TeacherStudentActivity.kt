package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.activity_teacher_student.*

class TeacherStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_teacher_student)
        btnstudent.setOnClickListener {
            startActivity(Intent(this@TeacherStudentActivity,ChooseClassActivity::class.java))
        }
        btnteacher.setOnClickListener(){
            startActivity(Intent(this@TeacherStudentActivity, RegistrationActivity::class.java))
            Toast.makeText(this@TeacherStudentActivity, "Auto Detecting location", Toast.LENGTH_LONG)
                .show()
        }
    }
}
