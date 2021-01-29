package com.bcebhagalpur.welcomeslider.student.starter.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.HomeActivity
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp_verify.*
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    /* private var lm: LocationManager? = null
    private var loc: Location? = null
    lateinit var sharedPreferences: SharedPreferences
    var location:String?=" "*/
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        // sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        setContentView(R.layout.activity_registration)

        imgBtnRegister.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, HomeActivity::class.java))
        }
        btn_location.setOnClickListener {
            requestPermission()
        }
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
                    et_user_address.setText("${address.getAddressLine(0)},${address.locality}")
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

// location=sharedPreferences.getString("location"," ")
//et_user_address.setText(location)
//Runtime permissions

// getLocation()
/*  btn_location.setOnClickListener {
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
}*/
