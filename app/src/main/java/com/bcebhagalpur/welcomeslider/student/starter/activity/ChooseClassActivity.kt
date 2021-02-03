package com.bcebhagalpur.welcomeslider.student.starter.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.fragment.Claas6thTo10thFragment
import com.bcebhagalpur.welcomeslider.fragment.Class13thFragment
import com.bcebhagalpur.welcomeslider.fragment.Class12thor11thFragment
import kotlinx.android.synthetic.main.activity_choose_class.*
import kotlinx.android.synthetic.main.activity_otp_verify.view.*
import kotlinx.android.synthetic.main.fragment_class13th.*

class  ChooseClassActivity : AppCompatActivity() {
    var classStudent:String?=""
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_choose_class)
        txt13th.setOnClickListener {
            classStudent = txt13th.text.toString()
            txt13th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Class13thFragment())
            transaction.commit()

        }
        txt12th.setOnClickListener() {
            classStudent = txt12th.text.toString()
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt13th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame, Class12thor11thFragment())
            transaction.commit()
        }
        txt11th.setOnClickListener() {
            classStudent = txt11th.text.toString()
            txt11th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Class12thor11thFragment())
            transaction.commit()
        }
        txt10th.setOnClickListener() {
            classStudent = txt10th.text.toString()
            txt10th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt9th.setOnClickListener() {
            classStudent = txt9th.text.toString()
            txt9th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt8th.setOnClickListener() {
            classStudent = txt8th.text.toString()
            txt8th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt7th.setOnClickListener() {
            classStudent = txt7th.text.toString()
            txt7th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt6th.setOnClickListener() {
            classStudent = txt6th.text.toString()
            txt6th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt5th.setOnClickListener() {
            classStudent = txt5th.text.toString()
            txt5th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt4th.setOnClickListener() {
            classStudent = txt4th.text.toString()
            txt4th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt3rd.setOnClickListener() {
            classStudent = txt3rd.text.toString()
            txt3rd.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt2nd.setOnClickListener() {
            classStudent = txt2nd.text.toString()
            txt2nd.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt1st . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }

        txt1st.setOnClickListener() {
            classStudent = txt1st.text.toString()
            txt1st.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txt11th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt10th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt9th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt8th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt7th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt6th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt5th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt4th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt3rd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt2nd . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            txt13th . setBackgroundResource (R.drawable.rounded_corners_imagebutton)
            val transaction =
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        /*btnStartLearning.setOnClickListener(){
            val intent=  Intent(this@ChooseClassActivity, RegistrationActivity::class.java)
            intent.putExtra("class", classStudent)
            startActivity(intent)
            Toast.makeText(this@ChooseClassActivity, "Auto detecting Location ", Toast.LENGTH_LONG).show()
        }*/



    }

    fun getResult(): String {
        return classStudent!!
    }


}
