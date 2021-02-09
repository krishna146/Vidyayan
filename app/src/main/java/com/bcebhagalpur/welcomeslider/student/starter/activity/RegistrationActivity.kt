package com.bcebhagalpur.welcomeslider.student.starter.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp_verify.*
import kotlinx.android.synthetic.main.activity_otp_verify.view.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import kotlinx.android.synthetic.main.activity_teacher_registration3.*
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mAuth: FirebaseAuth

    private lateinit var etStudentName:TextInputLayout
    private lateinit var etStudentEmail:TextInputLayout
    private lateinit var etStudentDob:TextInputLayout
    private lateinit var etStudentCity:TextInputLayout
    private lateinit var etStudentAddress:TextInputLayout
    private lateinit var imgBtnRegister:ImageButton
    private lateinit var actxtGender:AutoCompleteTextView
    private lateinit var imgBtnDob:ImageButton

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        etStudentName=findViewById(R.id.etStudentName)
        etStudentEmail=findViewById(R.id.etStudentEmail)
        etStudentDob=findViewById(R.id.etStudentDob)
        etStudentCity=findViewById(R.id.etStudentCity)
        etStudentAddress=findViewById(R.id.etStudentAddress)
        imgBtnRegister=findViewById(R.id.imgBtnRegister)
        imgBtnDob=findViewById(R.id.btnDob)
        actxtGender=findViewById(R.id.actxtGender)

        requestPermission()

        imgBtnDob.setOnClickListener {
            val formate = SimpleDateFormat("dd,mm,yyyy", Locale.US)
            val now=Calendar.getInstance()
            val datePicker= DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val selectedDate=Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date=formate.format(selectedDate.time)
                etStudentDob.editText!!.setText(date)
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        val studentClass = intent.getStringExtra("studentClass")
        val studentStream = intent.getStringExtra("studentStream")
        val studentBoard = intent.getStringExtra("studentBoard")
        val targetExam = intent.getStringExtra("targetExam")

        val genderItems = listOf("Male", "Female", "Neutral")
        val genderAdapter = ArrayAdapter(this, R.layout.list_item, genderItems)
      actxtGender.setAdapter(genderAdapter)


        imgBtnRegister.setOnClickListener {

            val name= etStudentName.editText!!.text.toString().trim()
            val email= etStudentEmail.editText!!.text.toString().trim()
            val dob= etStudentDob.editText!!.text.toString().trim()
            val city= etStudentCity.editText!!.text.toString().trim()
            val address= etStudentAddress.editText!!.text.toString().trim()

            mDatabase = FirebaseDatabase.getInstance()
            mDatabaseReference = mDatabase.reference.child("STUDENT")
            mAuth= FirebaseAuth.getInstance()
            val userId= mAuth.currentUser!!.uid
            val userNumber=mAuth.currentUser!!.phoneNumber

            if (name.isNotEmpty()&& email.isNotEmpty()&&dob.isNotEmpty()&& city.isNotEmpty()&& address.isNotEmpty()
                && actxtGender.text.isNotEmpty()) {
                mDatabaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val anotherChild = mDatabaseReference.child(userId)
                        anotherChild.child("userId").setValue(userId)
                        anotherChild.child("mobileNumber").setValue(userNumber)
                        anotherChild.child("studentName").setValue(name)
                        anotherChild.child("studentEmail").setValue(email)
                        anotherChild.child("studentDob").setValue(dob)
                        anotherChild.child("studentCity").setValue(city)
                        anotherChild.child("studentAddress").setValue(address)
                        anotherChild.child("studentGender").setValue(actxtGender.text.toString())
                        anotherChild.child("studentStream").setValue(studentStream)
                        anotherChild.child("studentClass").setValue(studentClass)
                        anotherChild.child("studentBoard").setValue(studentBoard)
                        anotherChild.child("targetExam").setValue(targetExam)

                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })
                mDatabase = FirebaseDatabase.getInstance()
                val users:DatabaseReference = mDatabase.reference.child("USERS")
                users.addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val anotherChild1=users.child(userId)
                        anotherChild1.child("userType").setValue("Student")
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

                startActivity(Intent(this@RegistrationActivity, HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun requestPermission() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.fastestInterval = 2000
        locationRequest.interval = 4000
        locationCallback = object : LocationCallback() {
            @SuppressLint("SetTextI18n")
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                LocationServices.getFusedLocationProviderClient(this@RegistrationActivity)
                    .removeLocationUpdates(this)
                if (p0 != null && p0.locations.size > 0) {
                    val latestLocationIndex = p0.locations.size - 1
                    val latitude = p0.locations[latestLocationIndex].latitude
                    val longitude = p0.locations[latestLocationIndex].longitude
                    val gc = Geocoder(this@RegistrationActivity, Locale.getDefault())
                    val addresses: List<Address> = gc.getFromLocation(
                        latitude,
                        longitude, 1
                    )
                    val address: Address = addresses[0]
                    etStudentAddress.editText!!.setText("${address.getAddressLine(0)},${address.locality}")
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                111
            )

            return

        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111) {

            if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    //lm = getSystemService(Context.LOCATION_SERVICE) as
                    // LocationManager
                    //loc =
                    Toast.makeText(
                        this, "permission allowed",
                        Toast.LENGTH_SHORT
                    ).show()
                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.getMainLooper()
                    )
                    // lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    //getLocation()
                    return
                } else {
                    Toast.makeText(
                        this, "some error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                    // btn_teacher_location.setOnClickListener(null)
                }

            } else {
                Toast.makeText(
                    this, "Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
                return
//                 btn_teacher_location.setOnClickListener(null)
            }

        }

    }
}


