@file:Suppress("DEPRECATION")
package com.bcebhagalpur.welcomeslider.teacher.starter.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.teacher.dashboard.activity.HomeTeacher
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_teacher_registration3.*
import java.util.*

@Suppress("DEPRECATION")
class TeacherRegistrationActivity3 : AppCompatActivity() {

    private lateinit var menuHighestQualification: TextInputLayout
    private lateinit var menuStatus: TextInputLayout
    private lateinit var menuCollege: TextInputLayout
    private lateinit var menuLanguage: TextInputLayout
    private lateinit var menuMode: TextInputLayout
    private lateinit var menuTiming: TextInputLayout
    private lateinit var menuPrice: TextInputLayout
    private lateinit var txtSelectClass: TextView
    private lateinit var txtSelectSubject: TextView

    private lateinit var actxtQualification: AutoCompleteTextView
    private lateinit var actxtStatus: AutoCompleteTextView
    private lateinit var actxtCollege: AutoCompleteTextView
    private lateinit var actxtLanguage: AutoCompleteTextView
    private lateinit var actxtMode: AutoCompleteTextView
    private lateinit var actxtTiming: AutoCompleteTextView
    private lateinit var actxtPrice: AutoCompleteTextView

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_registration3)

        menuHighestQualification = findViewById(R.id.menuHighestQualification)
        menuStatus = findViewById(R.id.menuStatus)
        menuCollege = findViewById(R.id.menuCollege)
        menuLanguage = findViewById(R.id.menuLanguage)
        menuMode = findViewById(R.id.menuMode)
        menuPrice = findViewById(R.id.menuPrice)
        menuTiming = findViewById(R.id.menuTiming)
        txtSelectClass = findViewById(R.id.txtSelectClass)
        txtSelectSubject = findViewById(R.id.txtSelectSubject)

        actxtQualification = findViewById(R.id.actxtQualification)
        actxtStatus = findViewById(R.id.actxtStatus)
        actxtCollege = findViewById(R.id.actxtCollege)
        actxtLanguage = findViewById(R.id.actxtLanguage)
        actxtMode = findViewById(R.id.actxtMode)
        actxtPrice = findViewById(R.id.actxtPrice)
        actxtTiming = findViewById(R.id.actxtTiming)

        changeColor()

        val qualificationItems = listOf("B.tech ", "M.tech", "B.sc", "B.A(English)")
        val qualificationAdapter = ArrayAdapter(this, R.layout.list_item, qualificationItems)
        actxtQualification.setAdapter(qualificationAdapter)

        val statusItems = listOf("Completed", "Pursuing")
        val statusAdapter = ArrayAdapter(this, R.layout.list_item, statusItems)
        actxtStatus.setAdapter(statusAdapter)

        val collegeItems =
            listOf("BCE Bhagalpur", "Aryabhatta Knowledge university", "S.M. College")
        val collegeAdapter = ArrayAdapter(this, R.layout.list_item, collegeItems)
        actxtCollege.setAdapter(collegeAdapter)

        val modeItems = listOf("online", "offline", "both")
        val modeAdapter = ArrayAdapter(this, R.layout.list_item, modeItems)
        actxtMode.setAdapter(modeAdapter)

        val languageItems = listOf(
            "English",
            "Hindi",
            "Both"
        )
        val languageAdapter = ArrayAdapter(this, R.layout.list_item, languageItems)
        actxtLanguage.setAdapter(languageAdapter)

        val priceItems = listOf(
            "500-1000",
            "1000-1200",
            "1200-1500",
            "1500-1800",
            "1800-2000",
            "2000-2500",
            "2500-3000",
            "3000-4000",
            "4000-5000",
            "5000-10,000"
        )
        val priceAdapter = ArrayAdapter(this, R.layout.list_item, priceItems)
        actxtPrice.setAdapter(priceAdapter)

        val timeItems = listOf(
            "6:00 AM to 8:00 AM",
            "8:00 AM to 10:00 AM",
            "10:00 AM to 12:00 AM",
            "12:00 AM to 2:00 PM",
            "2:00 PM to 4:00 PM",
            "4:00 PM to 6:00 PM",
            "6:00 PM to 8:00 PM",
            "8:00 AM to 10:00 PM"
        )
        val timeAdapter = ArrayAdapter(this, R.layout.list_item, timeItems)
        actxtTiming.setAdapter(timeAdapter)

        txtSelectClass.setOnClickListener {
            val subject = arrayOf(
                "1 to 5th",
                "1 to 8th",
                "1 to 10th",
                "1 to 12th",
                "6th to 8th",
                "6th to 10th",
                "6th to 12th",
                "9th to 10th",
                "9th to 12th",
                "11th to 12th"
            )
            val mAlertDialogBuilder =
                android.app.AlertDialog.Builder(this@TeacherRegistrationActivity3)
            mAlertDialogBuilder.setTitle("Select class")
            mAlertDialogBuilder.setItems(subject) { _, which ->
                when (which) {
                    which -> {
                        txtSelectClass.text = subject[which]
                        if (txtSelectClass.text == "1 to 12th" || txtSelectClass.text == "6th to 12th" || txtSelectClass.text == "9th to 12th" || txtSelectClass.text == "11th to 12th") {
                            txtSelectSubject.visibility = View.VISIBLE
                        } else {
                            txtSelectSubject.visibility = View.GONE
                        }
                    }
                }
            }
            val mAlertDialog = mAlertDialogBuilder.create()
            mAlertDialog.show()
        }

//        txtSelectSubject.setOnClickListener {
//            val subject1 = arrayOf("Mathematics", "Physics", "Biology", "Chemistry")
//            val checked = booleanArrayOf(false, false, false, false)
//            val mAlertDialogBuilder = AlertDialog.Builder(this@TeacherRegistrationActivity3)
//            mAlertDialogBuilder.setTitle("Select Subject for upper 10th class")
//            mAlertDialogBuilder.setItems(subject1) { _, which ->
//
//            }
//            mAlertDialogBuilder.setPositiveButton("Ok") { _, _ ->
//            }
//            val mAlertDialog = mAlertDialogBuilder.create()
//            mAlertDialog.show()
//        }
        txtSelectSubject.setOnClickListener {
            val inflateView = LayoutInflater.from(this).inflate(R.layout.check_subject_item, null)

            val mathematics = inflateView.findViewById<CheckBox>(R.id.mathematics)
            val physics = inflateView.findViewById<CheckBox>(R.id.physics)
            val chemistry = inflateView.findViewById<CheckBox>(R.id.chemistry)
            val biology = inflateView.findViewById<CheckBox>(R.id.biology)
            val subject = arrayOf(mathematics, physics, biology, chemistry)
            val subject1 = arrayOf("Mathematics", "Physics", "Biology", "Chemistry")
            var txt1 = ""
            var txt2 = ""
            var txt3 = ""
            var txt4 = ""

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("select subject")
            alertDialog.setView(inflateView)
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton("Ok") { _, _ ->
                if (subject[0].isChecked) {
                    txt1 = subject1[0]
                }
                if (subject[1].isChecked) {
                    txt2 = subject1[1]
                }
                if (subject[2].isChecked) {
                    txt3 = subject1[2]
                }
                if (subject[3].isChecked) {
                    txt4 = subject1[3]
                }

                txtSelectSubject.text = txt1+"" + txt2+"" + txt3+"" + txt4
            }
            val dialog = alertDialog.create()
            dialog.show()
        }

        btnSubmit.setOnClickListener {
            val teacherName = intent.getStringExtra("teacherName")
//                val mobileNumber = intent.getStringExtra("mobileNumber")
            val teacherEmail = intent.getStringExtra("teacherEmail")
            val teacherDob = intent.getStringExtra("teacherDob")
            val teacherGender = intent.getStringExtra("teacherGender")
            val teacherAddress = intent.getStringExtra("teacherAddress")
//                val teacherNumber = intent.getStringExtra("mobileNumber")
//                val userType = intent.getStringExtra("userType")
            val city = intent.getStringExtra("city")

            if (actxtQualification.text.isNotEmpty() && actxtStatus.text.isNotEmpty() && actxtCollege.text.isNotEmpty() && actxtLanguage.text.isNotEmpty()
                && actxtMode.text.isNotEmpty() && actxtPrice.text.isNotEmpty() && actxtTiming.text.isNotEmpty()
                && txtSelectClass.text != "Select Class") {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("VIDYAYAN")
                progressDialog.setMessage("We are processing, please wait")
                progressDialog.show()
                progressDialog.setCancelable(false)

                mDatabase = FirebaseDatabase.getInstance()
                mDatabaseReference = mDatabase.reference.child("TEACHERS")
                val currentUserDb = mDatabaseReference.child(city!!)
                val currentUserDb2 = currentUserDb.child(txtSelectClass.text.toString())
                mAuth = FirebaseAuth.getInstance()
                val userId = mAuth.currentUser!!.uid
                val userNumber = mAuth.currentUser!!.phoneNumber

                currentUserDb2.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val anotherChild = currentUserDb2.child(userId)
                        anotherChild.child("userId").setValue(userId)
                        anotherChild.child("mobileNumber").setValue(userNumber.toString())
                        anotherChild.child("teacherName").setValue(teacherName!!.toString())
                        anotherChild.child("teacherEmail").setValue(teacherEmail!!.toString())
                        anotherChild.child("teacherDob").setValue(teacherDob!!.toString())
                        anotherChild.child("teacherCity").setValue(city.toString())
                        anotherChild.child("teacherGender").setValue(teacherGender!!.toString())
                        anotherChild.child("teacherAddress")
                            .setValue(teacherAddress!!.toString())
                        anotherChild.child("qualification")
                            .setValue(actxtQualification.text.toString())
                        anotherChild.child("status").setValue(actxtStatus.text.toString())
                        anotherChild.child("language").setValue(actxtLanguage.text.toString())
                        anotherChild.child("college").setValue(actxtCollege.text.toString())
                        anotherChild.child("mode").setValue(actxtMode.text.toString())
                        anotherChild.child("class").setValue(txtSelectClass.text.toString())
                        anotherChild.child("subject").setValue(txtSelectSubject.text.toString())
                        anotherChild.child("timing").setValue(actxtTiming.text.toString())
                        anotherChild.child("price").setValue(actxtPrice.text.toString())
                        progressDialog.hide()
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

                val users1 = FirebaseDatabase.getInstance().reference.child("USERS")
               users1.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val anotherChild = currentUserDb2.child(userId)
                        anotherChild.child("userId").setValue(userId)
                        anotherChild.child("mobileNumber").setValue(userNumber.toString())
                        anotherChild.child("teacherName").setValue(teacherName!!.toString())
                        anotherChild.child("teacherEmail").setValue(teacherEmail!!.toString())
                        anotherChild.child("teacherDob").setValue(teacherDob!!.toString())
                        anotherChild.child("teacherCity").setValue(city.toString())
                        anotherChild.child("teacherGender").setValue(teacherGender!!.toString())
                        anotherChild.child("teacherAddress")
                            .setValue(teacherAddress!!.toString())
                        anotherChild.child("qualification")
                            .setValue(actxtQualification.text.toString())
                        anotherChild.child("status").setValue(actxtStatus.text.toString())
                        anotherChild.child("language").setValue(actxtLanguage.text.toString())
                        anotherChild.child("college").setValue(actxtCollege.text.toString())
                        anotherChild.child("mode").setValue(actxtMode.text.toString())
                        anotherChild.child("class").setValue(txtSelectClass.text.toString())
                        anotherChild.child("subject").setValue(txtSelectSubject.text.toString())
                        anotherChild.child("timing").setValue(actxtTiming.text.toString())
                        anotherChild.child("price").setValue(actxtPrice.text.toString())
                        progressDialog.hide()
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

                val users = FirebaseDatabase.getInstance().reference.child("USERS")
                users.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val anotherChild1 = users.child(userId)
                        anotherChild1.child("userType").setValue("Teacher")
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

                val intent = Intent(this@TeacherRegistrationActivity3, HomeTeacher::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "please enter fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.white)
        }
    }
}