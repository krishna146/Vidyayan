package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.activity_choose_class.*

class ChooseClassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_choose_class)
        six.setOnClickListener {
            startActivity(Intent(this@ChooseClassActivity,RegistrationActivity::class.java))
        }
        seven.setOnClickListener {
            startActivity(Intent(this@ChooseClassActivity,RegistrationActivity::class.java))
        }
        eight.setOnClickListener {
            startActivity(Intent(this@ChooseClassActivity,RegistrationActivity::class.java))
        }
        nine.setOnClickListener {
            startActivity(Intent(this@ChooseClassActivity,RegistrationActivity::class.java))
        }
        ten.setOnClickListener {
            startActivity(Intent(this@ChooseClassActivity,RegistrationActivity::class.java))
        }
        eleven.setOnClickListener {
            startActivity(Intent(this@ChooseClassActivity,RegistrationActivity::class.java))
        }
        twelve.setOnClickListener {
            startActivity(Intent(this@ChooseClassActivity,RegistrationActivity::class.java))
        }
    }
}
