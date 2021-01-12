package com.bcebhagalpur.welcomeslider.activity

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private var lm: LocationManager? = null
    private var loc: Location? = null
    lateinit var sharedPreferences: SharedPreferences
    var location:String?=" "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        setContentView(R.layout.activity_registration)

        imgBtnRegister.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity,HomeActivity::class.java))
        }

        location=sharedPreferences.getString("location"," ")
        et_user_address.setText(location)
        //Runtime permissions
       
        // getLocation()
        btn_location.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )!=
                PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    111
                )
            }else{
            getLocation()}
        }
    }
    val ll = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            reverseGeocode(location)
        }
        override fun onStatusChanged(provider: String?, status: Int, extras:
        Bundle?) {
        }
        override fun onProviderEnabled(provider: String?) {
        }
        override fun onProviderDisabled(provider: String?) {
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        try {
            lm = getSystemService(Context.LOCATION_SERVICE) as
                    LocationManager
            //
           // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,M
             //       ainActivity.this);
            loc = lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            lm!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                100.2f, ll)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        // }catch (Exception e){
        // e.printStackTrace();
        // }
    }
    @SuppressLint("SetTextI18n")
    private fun reverseGeocode(loc: Location?) {
        val gc = Geocoder(this@RegistrationActivity,
            Locale.getDefault())
        val addresses: List<Address> = gc.getFromLocation(loc!!.latitude,
            loc.longitude, 1)
        val address: Address = addresses[0]

        et_user_address.setText("${address.getAddressLine(0)},${address.locality}")

    }
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            111 -> {
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
                       // lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        //et_user_address.setText(location)
                        getLocation()
                    } else {
                        Toast.makeText(this, "some error occurred",
                            Toast.LENGTH_SHORT).show()
                       // btn_location.setOnClickListener(null)
                       // return
                    }
                    Toast.makeText(this, "permission allowed",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied",
                        Toast.LENGTH_SHORT).show()
                  //  btn_location.setOnClickListener(null)
                   // return
                }
                return
            }
        }
    }
}
