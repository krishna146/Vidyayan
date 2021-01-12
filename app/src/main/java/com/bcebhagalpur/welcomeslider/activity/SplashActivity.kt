package com.bcebhagalpur.welcomeslider.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import java.util.*

class SplashActivity : AppCompatActivity() {
    private var lm: LocationManager? = null
    private var loc: Location? = null
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
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
        // getLocation()
        // btn_teacher_location.setOnClickListener {
        getLocation()
        }
        // }
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity,
                MainActivity::class.java))
        }, 1500)
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

            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,M
                 //   ainActivity.this);
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
        val gc = Geocoder(this@SplashActivity, Locale.getDefault())
        val addresses: List<Address> = gc.getFromLocation(loc!!.latitude,
            loc.longitude, 1)
        val address: Address = addresses[0]

        //
        // et_teacher_address.setText("${address.getAddressLine(0)},${address.locality}
          //  ")

                    sharedPreferences.edit().putString("location","${address.getAddressLine(0)},${address.locality}").apply()
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
                       // lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        getLocation()
                    } else {
                        Toast.makeText(this, "some error occurred",
                            Toast.LENGTH_SHORT).show()
                        return
                        // btn_teacher_location.setOnClickListener(null)
                    }
                    Toast.makeText(this, "permission allowed",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied",
                        Toast.LENGTH_SHORT).show()
                    return
                    // btn_teacher_location.setOnClickListener(null)
                }

            }

    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}