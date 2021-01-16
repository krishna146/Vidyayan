package com.bcebhagalpur.welcomeslider.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bcebhagalpur.welcomeslider.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import kotlinx.android.synthetic.main.activity_teacher_student.*
import kotlinx.android.synthetic.main.teacher_registration_bottomsheet.*
import java.text.SimpleDateFormat
import java.util.*


class TeacherStudentActivity : AppCompatActivity() {
    var formate= SimpleDateFormat("dd,mm,yyyy", Locale.US)
    private lateinit var imgBtnTeacherRegistration:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_teacher_student)
//        imgBtnTeacherRegistration = findViewById(R.id.imgBtnTeacherRegister)

//        val bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet)
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//        bottomSheetBehavior.setPeekHeight(0)


        btnstudent.setOnClickListener {
            startActivity(Intent(this@TeacherStudentActivity, ChooseClassActivity::class.java))
        }
        btnteacher.setOnClickListener() {

            startActivity(Intent(this@TeacherStudentActivity, TeacherRegistrationActivity2::class.java))
            Toast.makeText(this@TeacherStudentActivity, "Auto Detecting location", Toast.LENGTH_LONG)
                .show()

//            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
//                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//            } else {
//                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//            }
        }

//        bottomSheetBehavior.setBottomSheetCallback(object :
//            BottomSheetBehavior.BottomSheetCallback() {
//
//            override fun onStateChanged(view: View, state: Int) {
//                when (state) {
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                    }
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                        btnteacher.visibility = View.VISIBLE
//                        btnstudent.visibility = View.VISIBLE
//                    }
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//
//                    }
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//
//                    }
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//
//                    }
//                }
//            }
//
//            override fun onSlide(view: View, p1: Float) {
//            }
//        })
//
//        btnDob.setOnClickListener {
//            val now = Calendar.getInstance()
//            val datePicker = DatePickerDialog(
//                this,
//                { _, year, month, dayOfMonth ->
//                    val selectedDate = Calendar.getInstance()
//                    selectedDate.set(Calendar.YEAR, year)
//                    selectedDate.set(Calendar.MONTH, month)
//                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                    val date = formate.format(selectedDate.time)
//                    etDob.setText(date)
//                },
//                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
//            )
//            datePicker.show()
//        }
//        txtGender.setOnClickListener {
//            val gender = arrayOf("Male", "Female", "Neutral")
//            val mAlertDialogBuilder = AlertDialog.Builder(this@TeacherStudentActivity)
//            mAlertDialogBuilder.setTitle("Select gender")
//            mAlertDialogBuilder.setItems(gender) { _, which ->
//                when (which) {
//                    which -> {
//                        txtGender.text = gender[which]
//                        txtGender.setTypeface(null, BOLD)
//                    }
//                }
//            }
//            val mAlertDialog = mAlertDialogBuilder.create()
//            mAlertDialog.show()
//        }
//
//        imgBtnTeacherRegistration.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//        }
//

    }


}
