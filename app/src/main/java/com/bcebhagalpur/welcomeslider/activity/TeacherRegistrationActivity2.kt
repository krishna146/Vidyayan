package com.bcebhagalpur.welcomeslider.activity

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
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_teacher_registration2.*
import java.text.SimpleDateFormat
import java.util.*

class TeacherRegistrationActivity2 : AppCompatActivity() {
    var formate = SimpleDateFormat("dd,mm,yyyy", Locale.US)
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.requestFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContentView(R.layout.activity_teacher_registration2)

        /* val gender= arrayOf("Gender","Male","Female","Neutral")
        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,gender)
        et_teacher_gender.threshold=0
        et_teacher_gender.setAdapter(arrayAdapter)
        et_teacher_gender.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if(hasFocus)  et_teacher_gender.showDropDown()
        })*/


        //   teacher_gender_spinner.adapter=arrayAdapter
        //   teacher_gender_spinner.onItemSelectedListener=object :
        /*AdapterView.OnItemSelectedListener{
             override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                et_teacher_gender.setText(gender[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }*/

       et_teacher_gender.setOnClickListener {
            val gender = arrayOf("Male", "Female", "Neutral")
            val mAlertDialogBuilder = AlertDialog.Builder(this)
            mAlertDialogBuilder.setTitle("Select gender")
            mAlertDialogBuilder.setItems(gender) { _, which ->
                when (which) {
                    which -> {
                       et_teacher_gender.setText(gender[which])
                        et_teacher_gender.setTypeface(null, BOLD)
                    }
                }
            }
            val mAlertDialog = mAlertDialogBuilder.create()
            mAlertDialog.show()
        }
//        teacher_qualification_btn.setOnClickListener {
//            val qualification = arrayOf("Btech", "Mtech", "Phd")
//            val mAlertDialogBuilder = AlertDialog.Builder(this)
//            mAlertDialogBuilder.setTitle("Select gender")
//            mAlertDialogBuilder.setItems(qualification) { _, which ->
//                when (which) {
//                    which -> {
//                        et_teacher_qualification.setText(qualification[which])
//                        et_teacher_qualification.setTypeface(null, BOLD)
//                    }
//                }
//            }
//            val mAlertDialog = mAlertDialogBuilder.create()
//            mAlertDialog.show()
//        }

        imgBtnTeacherRegister.setOnClickListener {
            startActivity(Intent(this@TeacherRegistrationActivity2,HomeTeacher::class.java))
        }
        et_teacher_address.setOnClickListener {
            requestPermission()
        }

       et_teacher_dob.setOnClickListener {
            val now=Calendar.getInstance()
            val datePicker=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
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
    }
    fun requestPermission()
    {
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)
        locationRequest= LocationRequest()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setFastestInterval(2000)
        locationRequest.setInterval(4000)
        locationCallback=object :LocationCallback(){
            @SuppressLint("SetTextI18n")
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                LocationServices.getFusedLocationProviderClient(this@TeacherRegistrationActivity2).removeLocationUpdates(this)
                if(p0!=null&&p0.locations.size>0){
                    var latestLocationIndex=p0.locations.size-1
                    var latitude=p0.locations.get(latestLocationIndex).latitude
                    var longitude=p0.locations.get(latestLocationIndex).longitude
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
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())

    }
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111) {

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
                    Toast.makeText(this, "permission allowed",
                        Toast.LENGTH_SHORT).show()
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())
                    // lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    //getLocation()
                    return
                } else {
                    Toast.makeText(this, "some error occurred",
                        Toast.LENGTH_SHORT).show()
                    return
                    // btn_teacher_location.setOnClickListener(null)
                }

            } else {
                Toast.makeText(this, "Permission Denied",
                    Toast.LENGTH_SHORT).show()
                return
                // btn_teacher_location.setOnClickListener(null)
            }

        }

    }

    }
