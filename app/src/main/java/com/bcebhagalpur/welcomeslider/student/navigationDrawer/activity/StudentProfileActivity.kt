package com.bcebhagalpur.welcomeslider.student.navigationDrawer.activity

import android.app.AlertDialog
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast

import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.fragment.Class11thor12thDropdownFragment
import com.bcebhagalpur.welcomeslider.fragment.Class12thor11thFragment
import com.bcebhagalpur.welcomeslider.fragment.Class13thDropdownFragment
import com.bcebhagalpur.welcomeslider.fragment.Class1to10thDropdownFragment
import kotlinx.android.synthetic.main.activity_student_profile.*
import kotlinx.android.synthetic.main.activity_teacher_registration2.*

class StudentProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
       // val autoCompleteTextView =
         //   findViewById<AutoCompleteTextView>(R.id.AutoCompleteTextView)

        val menuItems = listOf("13th", "12th", "11th", "10th", "9th","8th", "7th", "6th", "5th", "4th", "3rd", "2nd", "1st")
        val adapter = ArrayAdapter(this, R.layout.list_item, menuItems)
        autoCompleteTextView.setOnClickListener {
            val gender = arrayOf("13th", "12th", "11th", "10th", "9th","8th", "7th", "6th", "5th", "4th", "3rd", "2nd", "1st")
            val mAlertDialogBuilder = AlertDialog.Builder(this)
            mAlertDialogBuilder.setTitle("Select Class")
            mAlertDialogBuilder.setItems(gender) { _, which ->
                when (which) {
                    which -> {
                        et_class.setText(gender[which])
                        et_class.setTypeface(null, Typeface.BOLD)
                    }
                }
            }
            val mAlertDialog = mAlertDialogBuilder.create()
            mAlertDialog.show()
        }
       // autoCompleteTextView.setAdapter(adapter)

       // autoCompleteTextView.setText("Select Your Class", false)
       /* autoCompleteTextView.setOnItemClickListener { adapter, view, i, l ->
            var selectedItem = adapter.getItemAtPosition(i)
            if(selectedItem=="13th"){
                val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame1, Class13thDropdownFragment())
                transaction.commit()
            }
            if(selectedItem=="12th" || selectedItem== "11th"){
                val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame1, Class11thor12thDropdownFragment())
                transaction.commit()
            }
            if(selectedItem=="10th"||selectedItem=="9th"||selectedItem=="8th"||selectedItem=="7th"||selectedItem=="6th"||selectedItem=="5th"||selectedItem=="4th"||selectedItem=="3rd"||selectedItem=="2nd"||selectedItem=="1st"){
                val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame1, Class1to10thDropdownFragment())
                transaction.commit()
            }

        }*/

    }
}