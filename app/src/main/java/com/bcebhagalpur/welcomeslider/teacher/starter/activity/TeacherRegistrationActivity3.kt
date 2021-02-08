package com.bcebhagalpur.welcomeslider.teacher.starter.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.textclassifier.TextLanguage
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.activity.OtpVerifyActivity
import com.bcebhagalpur.welcomeslider.teacher.dashboard.activity.HomeTeacher
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_teacher_registration3.*
import java.util.*

class TeacherRegistrationActivity3 : AppCompatActivity() {

    private lateinit var menuHighestQualification:TextInputLayout
    private lateinit var menuStatus:TextInputLayout
    private lateinit var menuCollege:TextInputLayout
    private lateinit var menuLanguage: TextInputLayout
    private lateinit var menuMode:TextInputLayout
    private lateinit var txtSelectClass:TextView
    private lateinit var txtSelectSubject:TextView

    private lateinit var actxtQualification:AutoCompleteTextView
    private lateinit var actxtStatus:AutoCompleteTextView
    private lateinit var actxtCollege:AutoCompleteTextView
    private lateinit var actxtLanguage:AutoCompleteTextView
    private lateinit var actxtMode:AutoCompleteTextView

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_registration3)

        menuHighestQualification=findViewById(R.id.menuHighestQualification)
        menuStatus=findViewById(R.id.menuStatus)
        menuCollege=findViewById(R.id.menuCollege)
        menuLanguage=findViewById(R.id.menuLanguage)
        menuMode=findViewById(R.id.menuMode)
        txtSelectClass=findViewById(R.id.txtSelectClass)
        txtSelectSubject=findViewById(R.id.txtSelectSubject)

        actxtQualification=findViewById(R.id.actxtQualification)
        actxtStatus=findViewById(R.id.actxtStatus)
        actxtCollege=findViewById(R.id.actxtCollege)
        actxtLanguage=findViewById(R.id.actxtLanguage)
        actxtMode=findViewById(R.id.actxtMode)


        var b = 1

        val qualificationItems = listOf("B.tech ", "M.tech", "B.sc", "B.A(English)")
        val qualificationAdapter = ArrayAdapter(this, R.layout.list_item, qualificationItems)
       val qualification= actxtQualification.setAdapter(qualificationAdapter)

        val statusItems = listOf("Completed", "Pursuing")
        val statusAdapter = ArrayAdapter(this, R.layout.list_item, statusItems)
       val status= actxtStatus.setAdapter(statusAdapter)

        val collegeItems =
            listOf("BCE Bhagalpur", "Aryabhatta Knowledge university", "S.M. College")
        val collegeAdapter = ArrayAdapter(this, R.layout.list_item, collegeItems)
        val college=actxtCollege.setAdapter(collegeAdapter)

        val modeItems = listOf("online", "offline", "both")
        val modeAdapter = ArrayAdapter(this, R.layout.list_item, modeItems)
       val mode= actxtMode.setAdapter(modeAdapter)

        val languageItems = listOf("English", "Hindi", "Both")
        val languageAdapter = ArrayAdapter(this, R.layout.list_item, languageItems)
        val language=actxtLanguage.setAdapter(languageAdapter)

        var a = 0
        val classArray = arrayOf(
            "13th",
            "12th",
            "11th",
            "6-10th",
            "1-5th"
        )
        val classList = Arrays.asList(*classArray)

        // Boolean array for initial selected items
        val checkedClassArray = booleanArrayOf(
            false,
            false,
            false,
            false,
            false

        )
        val checkedSubArray13th = booleanArrayOf(
            false,
            false,
            false,
            false,
            false,
            false
        )
        val checkedSubArray12th = booleanArrayOf(
            false,
            false,
            false,
            false,
            false,
            false
        )
        var checkedSubArray11th = booleanArrayOf(
            false,
            false,
            false,
            false

        )
        var checkedSubArray6To10th = booleanArrayOf(
            false,
            false,
            false,
            false,
            false,
            false,
            false

        )
        var checkedSubArray1To5th = booleanArrayOf(
            false,
            false,
            false,
            false,
            false,
            false,
            false

        )

        txtSelectClass.setOnClickListener() {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Classes")

            builder.setMultiChoiceItems(classArray, checkedClassArray) { _, which, isChecked ->

                // Update the current focused item's checked status
                checkedClassArray[which] = isChecked

                if(isChecked && a ==1 ){
                    txtSelectSubject.visibility = View.VISIBLE
                    txtSelectSubject.text = ""
                    a = 1
                }
                if(which == 0 && isChecked){

                    val subArray = arrayOf("JEE (PHYSICS)",
                    "JEE (CHEMISTRY)",
                    "JEE (MATHEMATICS)",
                    "NEET (PHYSICS)",
                    "NEET (CHEMISTRY)",
                    "NEET (BIOLOGY)"
                    )
                    val subList = Arrays.asList(*subArray)

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Select 13th Subjects")
                    builder.setMultiChoiceItems(subArray, checkedSubArray13th) { _, which, isChecked ->

                        // Update the current focused item's checked status
                        checkedSubArray13th[which] = isChecked

                    }

                    builder.setPositiveButton("OK") { _, _ ->

                        txtSelectSubject.text = ""
                        for (i in checkedSubArray13th.indices) {

                            val checked = checkedSubArray13th[i]
                            //checkedSubArray13th.set(i, checked)

                            if (checked) {

                                txtSelectSubject.visibility = View.VISIBLE
                                txtSelectSubject.text =
                                    txtSelectSubject.text.toString() + subList[i] + "  "
                                txtSelectSubject.gravity = Gravity.START
                                txtSelectSubject.setTextSize(TypedValue.COMPLEX_UNIT_PT,5F);

                            }

                        }
                    }

                    builder.setNeutralButton("Cancel") { _, which ->

                    }
                    val dialog = builder.create()
                    dialog.show()


                }
                if(which == 1 && isChecked){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Select Subjects")

                    val classList = arrayOf(
                        "12th Physics",
                        "12h Chemistry",
                        "12th Mathematics",
                        "12th Biology"
                    )
                    val subList = Arrays.asList(*classList)

                    builder.setMultiChoiceItems(classList, checkedSubArray12th) { _, which, isChecked ->

                        checkedSubArray12th[which] = isChecked

                    }

                    builder.setPositiveButton("OK") { _, _ ->

                        for (i in checkedSubArray12th.indices) {

                            val checked = checkedSubArray12th[i]
                            if (checked) {
                                txtSelectSubject.visibility = View.VISIBLE
                                txtSelectSubject.text =
                                    txtSelectSubject.text.toString() + subList[i] + "  "
                                txtSelectSubject.gravity = Gravity.START
                                txtSelectSubject.setTextSize(TypedValue.COMPLEX_UNIT_PT,5F);
                            }
                        }
                    }


                    builder.setNeutralButton("Cancel") { _, _ ->

                    }

                    val dialog1 = builder.create()
                    dialog1.show()
                }
                if(which == 2 && isChecked){

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Select Subjects")

                    val subArray = arrayOf(
                        "11th Physics",
                        "11h Chemistry",
                        "11th Mathematics",
                        "11th Biology"
                    )
                    val subList = Arrays.asList(*subArray)

                    builder.setMultiChoiceItems(subArray, checkedSubArray11th) { _, which, isChecked ->

                        // Update the current focused item's checked status
                        checkedSubArray11th[which] = isChecked

                    }


                    builder.setPositiveButton("OK") { _, _ ->

                        for (i in checkedSubArray11th.indices) {

                            val checked = checkedSubArray11th[i]
                            //checkedSubArray13th.set(i, checked)

                            if (checked) {

                                txtSelectSubject.text =
                                    txtSelectSubject.text.toString() + subList[i] + "  "
                                txtSelectSubject.gravity = Gravity.START
                                txtSelectSubject.setTextSize(TypedValue.COMPLEX_UNIT_PT,5F);

                            }
                        }
                    }


                    builder.setNeutralButton("Cancel") { _, _ ->

                    }
                    val dialog = builder.create()
                    // Display the alert dialog on interface
                    dialog.show()
                }
                if(which == 3 && isChecked){
                    val subArray = arrayOf(
                        "6-10th Science",
                        "6-10th Social Science",
                        "6-10th Mathematics",
                        "6-10th English",
                        "6-10th Hindi",
                        "6-10th Sanskrit",
                        "6-10th Computer "
                    )
                    val subList = Arrays.asList(*subArray)

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Select Subjects")

                    builder.setMultiChoiceItems(subArray, checkedSubArray6To10th) { _, which, isChecked ->

                        // Update the current focused item's checked status
                        checkedSubArray6To10th[which] = isChecked

                    }

                    builder.setPositiveButton("OK") { _, _ ->

                        for (i in checkedSubArray6To10th.indices) {

                            val checked = checkedSubArray6To10th[i]
                            if (checked) {

                                txtSelectSubject.text =
                                    txtSelectSubject.text.toString() + subList[i] + "  "
                                txtSelectSubject.gravity = Gravity.START
                                txtSelectSubject.setTextSize(TypedValue.COMPLEX_UNIT_PT,5F)

                            }
                        }
                    }

                    builder.setNeutralButton("Cancel") { _, _ ->

                    }

                    val dialog = builder.create()
                    dialog.show()
                }
                if(which == 4 && isChecked){
                    val subArray = arrayOf(
                        "1-5th Science",
                        "1-5th Social Science",
                        "1-5th Mathematics",
                        "1-5th English",
                        "1-5th Hindi",
                        "1-5th Sanskrit",
                        "1-5th Computer"
                    )
                    val subList = Arrays.asList(*subArray)

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Select Subjects")

                    builder.setMultiChoiceItems(subArray, checkedSubArray1To5th) { _, which, isChecked ->

                        // Update the current focused item's checked status
                        checkedSubArray1To5th[which] = isChecked

                    }

                    builder.setPositiveButton("OK") { _, _ ->

                        for (i in checkedSubArray6To10th.indices) {

                            val checked = checkedSubArray1To5th[i]
                            if (checked) {

                                txtSelectSubject.text =
                                    txtSelectSubject.text.toString() + subList[i] + "  "
                                txtSelectSubject.gravity = Gravity.START
                                txtSelectSubject.setTextSize(TypedValue.COMPLEX_UNIT_PT,5F)

                            }
                        }
                    }

                    builder.setNeutralButton("Cancel") { _, _ ->

                    }

                    val dialog = builder.create()
                    dialog.show()

                }

            }

            builder.setPositiveButton("OK") { _, which ->


                for (i in checkedClassArray.indices) {

                    val checked = checkedClassArray[i]
                    // checkedClassArray[i] = checked
                    if (checked) {
                        if(b == 1){
                            txtSelectClass.text = ""

                        }
                        txtSelectClass.setTextSize(TypedValue.COMPLEX_UNIT_PT,10F)
                        txtSelectClass.visibility = View.VISIBLE
                        txtSelectClass.gravity = Gravity.START
                        txtSelectClass.text = txtSelectClass.text.toString() + " " + classList[i]

                        b = 2
                    }

                }

                if (!checkedClassArray[0] && !checkedClassArray[1] && !checkedClassArray[2] && !checkedClassArray[3] && !checkedClassArray[4]) {

                    txtSelectClass.text = "Select Classes"
                    txtSelectSubject.visibility = View.GONE
                    txtSelectSubject.text = "Select Classes"
                    txtSelectSubject.gravity = Gravity.CENTER
                    txtSelectSubject.setTextSize(TypedValue.COMPLEX_UNIT_PT,20F)


                }

            }

            builder.setNeutralButton("Cancel") { _, which ->

            }

            val dialog = builder.create()
            dialog.show()

        }

            btnSubmit.setOnClickListener {


                val teacherName=intent.getStringExtra("teacherName")
                val mobileNumber=intent.getStringExtra("mobileNumber")
                val teacherEmail=intent.getStringExtra("teacherEmail")
                val teacherDob=intent.getStringExtra("teacherDob")
                val teacherGender=intent.getStringExtra("teacherGender")
                val teacherAddress=intent.getStringExtra("teacherAddress")
                val teacherNumber=intent.getStringExtra("mobileNumber")
                val userType=intent.getStringExtra("userType")
                val city=intent.getStringExtra("city")

                mDatabase = FirebaseDatabase.getInstance()
                mDatabaseReference = mDatabase.reference.child("TEACHERS")
                val currentUserDb = mDatabaseReference.child(city!!)
                mAuth= FirebaseAuth.getInstance()
                val userId= mAuth.currentUser!!.uid


                currentUserDb.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val anotherChild = currentUserDb.child(userId)
                        anotherChild.child("userId").setValue(userId)
                        anotherChild.child("mobileNumber").setValue(teacherNumber!!.toString())
                        anotherChild.child("teacherName").setValue(teacherName!!.toString())
                        anotherChild.child("teacherEmail").setValue(teacherEmail!!.toString())
                        anotherChild.child("teacherDob").setValue(teacherDob!!.toString())
                        anotherChild.child("teacherCity").setValue(city.toString())
                        anotherChild.child("teacherGender").setValue(teacherGender!!.toString())
                        anotherChild.child("teacherAddress").setValue(teacherAddress!!.toString())
                        anotherChild.child("qualification").setValue(qualification.toString())
                        anotherChild.child("status").setValue(status.toString())
                        anotherChild.child("language").setValue(language.toString())
                        anotherChild.child("college").setValue(college.toString())
                        anotherChild.child("mode").setValue(mode.toString())
//                        anotherChild.child("class").setValue(selectClass.toString())
//                        anotherChild.child("subject").setValue(subject.toString())
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

                mDatabase = FirebaseDatabase.getInstance()
                val users:DatabaseReference = mDatabase.reference.child("USERS")
               users.addListenerForSingleValueEvent(object :ValueEventListener{
                   override fun onDataChange(snapshot: DataSnapshot) {
                       val anotherChild1=users.child(userId)
                       anotherChild1.child("userType").setValue("Teacher")
                   }

                   override fun onCancelled(error: DatabaseError) {
                   }
               })
                val intent=Intent(this@TeacherRegistrationActivity3, HomeTeacher::class.java)
                startActivity(intent)
                finish()
//                intent.putExtra("teacherName",teacherName)
//                intent.putExtra("mobileNumber",mobileNumber)
//                intent.putExtra("teacherEmail",teacherEmail)
//                intent.putExtra("teacherDob",teacherDob)
//                intent.putExtra("teacherGender",teacherGender)
//                intent.putExtra("teacherAddress",teacherAddress)
//                intent.putExtra("teacherNumber",teacherNumber)
//                intent.putExtra("userType",userType)
//                intent.putExtra("city",city)
//                intent.putExtra("college",college.toString())
//                intent.putExtra("qualification",qualification.toString())
//                intent.putExtra("status",status.toString())
//                intent.putExtra("mode",mode.toString())
//                intent.putExtra("langauge",language.toString())
//                intent.putExtra("class",txtSelectClass.text.toString())
//                intent.putExtra("subject",txtSelectSubject.text.toString())
            }
        }
    }