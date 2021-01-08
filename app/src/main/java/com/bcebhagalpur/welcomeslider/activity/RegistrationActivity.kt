package com.bcebhagalpur.welcomeslider.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*
import java.util.jar.Manifest

class RegistrationActivity : AppCompatActivity() {
    lateinit var lm: LocationManager
    lateinit var loc: Location
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_registration)

        imgBtnLogin.setOnClickListener(){
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                111
            )
        }
        lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        var ll = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                reverseGeocode(location)
            }


            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                TODO("Not yet implemented")
            }

            override fun onProviderEnabled(provider: String?) {
                TODO("Not yet implemented")
            }

            override fun onProviderDisabled(provider: String?) {
                TODO("Not yet implemented")
            }


        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100.2f, ll)

    }

    private fun reverseGeocode(loc: Location?) {
        var gc = Geocoder(this@RegistrationActivity, Locale.getDefault())
        var addresses: List<Address> = gc.getFromLocation(loc!!.latitude, loc.longitude, 2)
        var address: Address = addresses.get(0)
        et_user_address.setText("${address.getAddressLine(0)},${address.locality}")
    }
}