package com.bcebhagalpur.welcomeslider.activity

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
    private var lm:LocationManager?=null
    private var loc:Location?=null

    private lateinit var imgBtnRegister:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_registration)

        imgBtnRegister=findViewById(R.id.imgBtnRegister)

        imgBtnRegister.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity,HomeActivity::class.java))
        }

        if(ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),111)
            return
        }
        else{
            lm=getSystemService(Context.LOCATION_SERVICE) as LocationManager
            loc=lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }


        val ll=object:LocationListener{
            override fun onLocationChanged(location: Location?) {
                reverseGeocode(location)
            }



            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {
            }


        }
        try {
            lm!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,100.2f,ll)
        }catch(e:NullPointerException){
            e.printStackTrace()
        }

    }
    @SuppressLint("SetTextI18n")
    private fun reverseGeocode(loc:Location?) {
        val gc=Geocoder(this@RegistrationActivity, Locale.getDefault())
        val addresses:List<Address> =gc.getFromLocation(loc!!.latitude,loc.longitude,2)
        val address:Address= addresses[0]
        et_user_address.setText("${address.getAddressLine(0)},${address.locality}")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            111 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED ) {
                    if(ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                    {
                        lm=getSystemService(Context.LOCATION_SERVICE) as LocationManager
                        loc=lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        Toast.makeText(this, "permission allowed", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "some error occurred", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}