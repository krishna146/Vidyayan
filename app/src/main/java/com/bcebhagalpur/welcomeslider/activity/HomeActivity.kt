package com.bcebhagalpur.welcomeslider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bcebhagalpur.welcomeslider.R

class HomeActivity : AppCompatActivity() {

    val countryCode= arrayOf("+91","+355","+213","+376","+244","+1264",
    "+1268","+54","+374","+297","+61","+43","+994","+1242","+973","+880","+1246","+375",
    "+32","+501","+229","+1441","+975","+591","+387","+267","+55","+673","+359","+226","+257",
    "+855","+237","+1","+238","+1345","+236","+235","+56","+86","+57","+269")

    private lateinit var txtCountryCode:TextView
    private lateinit var etMobileNumber:TextView
    private lateinit var btnNext:ImageButton
    private lateinit var btnRegister:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_home)

        txtCountryCode=findViewById(R.id.txtCountryCode)
        etMobileNumber=findViewById(R.id.etMobileNumber)
        btnNext=findViewById(R.id.btnNext)
        btnRegister=findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ChooseClassActivity::class.java))
        }

            txtCountryCode.setOnClickListener {
                val mAlertDialogBuilder=AlertDialog.Builder(this@HomeActivity)
                mAlertDialogBuilder.setTitle("Choose your country code")
                mAlertDialogBuilder.setItems(countryCode){_,which->
                    when(which){
                        which->{
                            txtCountryCode.setText(countryCode[which])
                        }
                    }
                }
                val mAlertDialog=mAlertDialogBuilder.create()
                mAlertDialog.show()
            }
        }
}
