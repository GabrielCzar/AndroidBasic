package gabrielczar.com.getlocation

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.*


@RuntimePermissions
class MainActivity : AppCompatActivity() {
    var mLocation : LocationRequest? = null

    private var UPDATE_INTERVAL = 10 * 1000L // 10 secs
    private var FATEST_INTERVAL = 2  * 1000L  // 2 secs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startLocationUpdate()

        btnLocation.setOnClickListener {
            Log.d("TEST", "TESt")
            txtViewLocation.text = "TEST"
            showLocationWithPermissionCheck()
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdate() {
        mLocation = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FATEST_INTERVAL)

        var buider : LocationSettingsRequest.Builder = LocationSettingsRequest.Builder().addLocationRequest(mLocation!!)
        var lRequest : LocationSettingsRequest = buider.build()

        var settingsClient : SettingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(lRequest)

        getFusedLocationProviderClient(this)
                .requestLocationUpdates(mLocation,
                        LocationCallback(),
                        Looper.myLooper())

    }

    @NeedsPermission(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)
    fun showLocation() {
        //Método que solicita a permissão e caso ela seja garantida
        Log.d("PERMISSION", "GRANTED")

        getLocation()
    }

    @SuppressLint("MissingPermission")
    fun getLocation(){
        val locationClient = getFusedLocationProviderClient(this)

        locationClient
                .lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            var lat = location.latitude
                            var lon = location.longitude
                            var alt = location.altitude
                            txtViewLocation.text = String.format("Lat: %f \nLon: %f \nAlt: %f", lat, lon, alt)

                        }
                    }.addOnFailureListener { e ->
                        Toast.makeText(this, "Error has occurred, impossible find location", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showRationaleForLocation(request: PermissionRequest) {
        //Este método server para exibir uma mensagem ao usuário antes do Dialog que irá
        AlertDialog.Builder(this)
                .setPositiveButton("YES") { _, _ -> request.proceed() }
                .setNegativeButton("NO") { _, _ -> request.cancel() }
                .setCancelable(false)
                .setMessage("PERMISSION")
                .show()
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onLocationDenied () {
        Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onLocationNeverAskAgain() {
        Toast.makeText(this, "NEVER ASK", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

}