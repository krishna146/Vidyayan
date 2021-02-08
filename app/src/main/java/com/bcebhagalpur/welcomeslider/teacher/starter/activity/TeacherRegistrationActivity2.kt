package com.bcebhagalpur.welcomeslider.teacher.starter.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface.BOLD
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_student_profile.*
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import java.text.SimpleDateFormat
import java.util.*

class TeacherRegistrationActivity2 : AppCompatActivity() {
    var formate = SimpleDateFormat("dd,mm,yyyy", Locale.US)
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var acTextGender: AutoCompleteTextView
    private lateinit var et_teacher_name:TextInputEditText
    private lateinit var et_teacher_email:TextInputEditText
    private lateinit var et_teacher_gender:AutoCompleteTextView
    private lateinit var et_teacher_dob:TextInputEditText
    private lateinit var et_teacher_address:TextInputEditText
    private lateinit var txtSelectCity:TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_registration2)
        acTextGender = findViewById(R.id.acTxtGender)
        et_teacher_name=findViewById(R.id.et_teacher_name)
        et_teacher_email=findViewById(R.id.et_teacher_email)
        et_teacher_dob=findViewById(R.id.et_teacher_dob)
        et_teacher_gender=findViewById(R.id.acTxtGender)
        et_teacher_address=findViewById(R.id.et_teacher_address)
        txtSelectCity=findViewById(R.id.txtSelectCity)
        emailValidator()
        requestPermission()


//        val teacherName= et_teacher_name.text.toString()
//        val teacherEmail= et_teacher_email.text.toString()
//        val teacherGender= et_teacher_gender.text.toString()
//        val teacherDob= et_teacher_dob.text.toString()
//        val teacherAddress= et_teacher_address.text.toString()

        txtSelectCity.setOnClickListener {
            val city= arrayOf("Araria","Arwal","Aurangabad","Banka","Begusarai","Bhagalpur","Bhojpur",
                "Buxar","Darbhanga","East Champaran (Motihari)","Gaya","Gopalganj","Jamui","Jehanabad",
                "Kaimur (Bhabua)", "Katihar","Khagaria","Kishanganj","Lakhisarai","Madhepura","Madhubani",
                "Munger","Muzaffarpur","Nalanda","Nawada","Patna","Purnia","Rohtas","Saharsa","Samastipur","Saran",
                "Sheikhpura", "Sheohar", "Sitamarhi", "Siwan", "Supaul", "Vaishali", "West Champara")
            val mAlertDialogBuilder=AlertDialog.Builder(this@TeacherRegistrationActivity2)
            mAlertDialogBuilder.setTitle("search city")
            mAlertDialogBuilder.setItems(city){_,which->
                when(which){
                    which->{
                        txtSelectCity.setText(city[which])
                    }
                }
            }
            val mAlertDialog=mAlertDialogBuilder.create()
            mAlertDialog.show()
        }

        val genderItems = listOf("Male", "Female", "Neutral")
        val genderAdapter = ArrayAdapter(this, R.layout.list_item, genderItems)
        val gender=acTxtGender.setAdapter(genderAdapter)


       et_teacher_dob.setOnClickListener {
            val now=Calendar.getInstance()
            val datePicker=DatePickerDialog(this, { _, year, month, dayOfMonth ->
             val selectedDate=Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date=formate.format(selectedDate.time)
               et_teacher_dob.setText(date)
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)
        locationRequest= LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.fastestInterval = 2000
        locationRequest.interval = 4000
        locationCallback=object :LocationCallback(){
            @SuppressLint("SetTextI18n")
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                LocationServices.getFusedLocationProviderClient(this@TeacherRegistrationActivity2).removeLocationUpdates(this)
                if(p0!=null&&p0.locations.size>0){
                    val latestLocationIndex=p0.locations.size-1
                    val latitude= p0.locations[latestLocationIndex].latitude
                    val longitude= p0.locations[latestLocationIndex].longitude
                    val gc = Geocoder(this@TeacherRegistrationActivity2, Locale.getDefault())
                    val addresses: List<Address> = gc.getFromLocation(latitude,
                        longitude, 1)
                    val address: Address = addresses[0]
                    et_teacher_address.setText("${address.getAddressLine(0)},${address.locality}")
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
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())

        imgBtnTeacherNext.setOnClickListener {


            if(et_teacher_name.text.toString().isEmpty() || et_teacher_email.text.toString().toString().isEmpty() || et_teacher_dob.text.toString().isEmpty() || acTextGender.text.toString().isEmpty() || txtSelectCity.text== "Select City" || et_teacher_address.text.toString().isEmpty()){

                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }

                if(et_teacher_name.text.toString().isNotEmpty()  && et_teacher_email.text.toString().toString().isNotEmpty() && et_teacher_dob.text.toString().isNotEmpty() && et_teacher_gender.text.toString().isNotEmpty() && txtSelectCity.text!= "Select City" && et_teacher_address.text.toString().isNotEmpty()){

                val userType=intent.getStringExtra("userType")
                val number=intent.getStringExtra("mobileNumber")
                val intent1=Intent(this,TeacherRegistrationActivity3::class.java)
                intent1.putExtra("teacherName",et_teacher_name.text.toString())
                intent1.putExtra("teacherEmail",et_teacher_email.text.toString())
                intent1.putExtra("teacherDob",et_teacher_dob.text.toString())
                intent1.putExtra("teacherGender",acTextGender.text.toString())
                intent1.putExtra("teacherAddress",et_teacher_address.text.toString())
                intent1.putExtra("city",txtSelectCity.text.toString())
                intent1.putExtra("mobileNumber",number)
                intent1.putExtra("userType",userType)
                startActivity(intent1)
            }

        }
    }


    private fun emailValidator(){

        et_teacher_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_teacher_email.text.toString()).matches()){
                    imgBtnTeacherNext.isEnabled=true
                }else {
                    et_teacher_email.error="invalid email!!"
                }
                   // Toast.makeText(this@TeacherRegistrationActivity2, "Invalid Email!!", Toast.LENGTH_LONG).show()                }
            }

        })
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
                LocationServices.getFusedLocationProviderClient(this@TeacherRegistrationActivity2)
                    .removeLocationUpdates(this)
                if (p0 != null && p0.locations.size > 0) {
                    val latestLocationIndex = p0.locations.size - 1
                    val latitude = p0.locations.get(latestLocationIndex).latitude
                    val longitude = p0.locations.get(latestLocationIndex).longitude
                    val gc = Geocoder(this@TeacherRegistrationActivity2, Locale.getDefault())
                    val addresses: List<Address> = gc.getFromLocation(
                        latitude,
                        longitude, 1
                    )
                    val address: Address = addresses[0]
                    et_teacher_address.setText("${address.getAddressLine(0)},${address.locality}")
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
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