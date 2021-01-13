package com.bcebhagalpur.welcomeslider.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import java.text.SimpleDateFormat
import java.util.*

class TeacherRegistrationActivity2 : AppCompatActivity() {
    var formate=SimpleDateFormat("dd,mm,yyyy",Locale.US)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(R.layout.activity_teacher_registration2)
        val gender= arrayOf("Gender","Male","Female","Neutral")
        val qualification=arrayOf("Qualification","Btech","Mtech","Phd")
         val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,gender)
        val arrayAdapter1=ArrayAdapter(this,android.R.layout.simple_list_item_1,qualification)
        spi_gender.adapter=arrayAdapter
        spi_qualification.adapter=arrayAdapter1
        spi_gender.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@TeacherRegistrationActivity2,gender.get(position).toString(),Toast.LENGTH_SHORT).show()
            }

        }
        spi_qualification.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@TeacherRegistrationActivity2,qualification.get(position).toString(),Toast.LENGTH_SHORT).show()
            }

        }
        imgBtnTeacherRegister.setOnClickListener {
            startActivity(Intent(this@TeacherRegistrationActivity2,HomeActivity::class.java))
        }

        btn_age.setOnClickListener {
            val now=Calendar.getInstance()
            val datePicker=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
             val selectedDate=Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date=formate.format(selectedDate.time)
                et_teacher_age.setText(date)
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
    }


}