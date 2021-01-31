package com.bcebhagalpur.welcomeslider.student.starter.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp_verify.*
import kotlinx.android.synthetic.main.activity_otp_verify.view.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import kotlinx.android.synthetic.main.activity_teacher_registration3.*
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    /* private var lm: LocationManager? = null
    private var loc: Location? = null
    lateinit var sharedPreferences: SharedPreferences
    var location:String?=" "*/

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mAuth: FirebaseAuth

    private lateinit var etStudentName:TextInputLayout
    private lateinit var etStudentEmail:TextInputLayout
    private lateinit var etStudentDob:TextInputLayout
    private lateinit var etStudentCity:TextInputLayout
    private lateinit var etStudentAddress:TextInputLayout
    private lateinit var imgBtnRegister:ImageButton
    private lateinit var imgBtnDob:ImageButton

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.requestFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
        // sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        setContentView(R.layout.activity_registration)

        etStudentName=findViewById(R.id.etStudentName)
        etStudentEmail=findViewById(R.id.etStudentEmail)
        etStudentDob=findViewById(R.id.etStudentDob)
        etStudentCity=findViewById(R.id.etStudentCity)
        etStudentAddress=findViewById(R.id.etStudentAddress)
        imgBtnRegister=findViewById(R.id.imgBtnRegister)
        imgBtnDob=findViewById(R.id.btnDob)

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

        var studentClass = intent.getStringExtra("studentClass")
        var studentStream = intent.getStringExtra("studentStream")
        var studentBoard = intent.getStringExtra("studentBoard")
        var targetExam = intent.getStringExtra("targetExam")
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
            val currentUserDb = mDatabaseReference.child(city)
            mAuth= FirebaseAuth.getInstance()
            val userId= mAuth.currentUser!!.uid

            if (name!==""&& email!==""&&dob!==""&& city!==""&& address!=="") {
                currentUserDb.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val anotherChild = currentUserDb.child(userId)
                        anotherChild.child("userId").setValue(userId)
                        anotherChild.child("mobileNumber").setValue(name)
                        anotherChild.child("teacherName").setValue(email)
                        anotherChild.child("teacherEmail").setValue(dob)
                        anotherChild.child("teacherDob").setValue(address)
                        anotherChild.child("teacherCity").setValue(city)
                        anotherChild.child("teacherGender").setValue(studentClass)
                        anotherChild.child("teacherAddress").setValue(studentBoard)
                        anotherChild.child("qualification").setValue(studentStream)
                        anotherChild.child("status").setValue(targetExam)
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })
                val student=FirebaseDatabase.getInstance().reference.child("checkStudent")
                student.addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        student.child(userId).setValue(userId)
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })
                val student1=FirebaseDatabase.getInstance().reference.child("user")
                student.addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val child=student1.child(userId)
                        child.child("Student").setValue("Student")
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })

                startActivity(Intent(this@RegistrationActivity, HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"000000000000eji",Toast.LENGTH_SHORT).show()
            }
        }
            requestPermission()
    }

    fun requestPermission() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setFastestInterval(2000)
        locationRequest.setInterval(4000)
        locationCallback = object : LocationCallback() {
            @SuppressLint("SetTextI18n")
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                LocationServices.getFusedLocationProviderClient(this@RegistrationActivity)
                    .removeLocationUpdates(this)
                if (p0 != null && p0.locations.size > 0) {
                    var latestLocationIndex = p0.locations.size - 1
                    var latitude = p0.locations.get(latestLocationIndex).latitude
                    var longitude = p0.locations.get(latestLocationIndex).longitude
                    val gc = Geocoder(this@RegistrationActivity, Locale.getDefault())
                    val addresses: List<Address> = gc.getFromLocation(
                        latitude,
                        longitude, 1
                    )
                    val address: Address = addresses[0]
                    etStudentAddress.txt("${address.getAddressLine(0)},${address.locality}")
                    //  txt_location.text=" ${address.getAddressLine(0)},${address.locality}"
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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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
                // btn_teacher_location.setOnClickListener(null)
            }

        }

    }
}

private fun TextInputLayout.txt(s: String) {

}

