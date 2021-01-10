package com.bcebhagalpur.welcomeslider.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.fragment.Claas6thTo10thFragment
import com.bcebhagalpur.welcomeslider.fragment.Class11thFragment
import com.bcebhagalpur.welcomeslider.fragment.Class12thFragment
import com.bcebhagalpur.welcomeslider.fragment.Class13thFragment
import kotlinx.android.synthetic.main.activity_choose_class.*

class ChooseClassActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_choose_class)
        txt13th.setOnClickListener() {

            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Class13thFragment())
            transaction.commit()
            txt13th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)

        }
        txt12th.setOnClickListener() {
            txt12th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Class12thFragment())
            transaction.commit()
        }
        txt11th.setOnClickListener() {
            txt11th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Class11thFragment())
            transaction.commit()
        }
        txt10th.setOnClickListener(){
            txt10th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
            supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
           txt9th.setOnClickListener(){
               txt9th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
            supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt8th.setOnClickListener(){
            txt8th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt7th.setOnClickListener(){
            txt7th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt6th.setOnClickListener(){
            txt6th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt5th.setOnClickListener(){
            txt5th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt4th.setOnClickListener(){
            txt4th.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt3rd.setOnClickListener(){
            txt3rd.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt2nd.setOnClickListener(){
            txt2nd.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }
        txt1st.setOnClickListener(){
            txt1st.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            val transaction =
                supportFragmentManager.beginTransaction().replace(R.id.frame, Claas6thTo10thFragment())
            transaction.commit()
        }


    }
}
