package com.example.gosporty_android_project.view

import android.annotation.SuppressLint
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.ActivityMapBinding
import com.example.gosporty_android_project.view.viewmodels.EstablishmentViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.OnMapsSdkInitializedCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback, OnMapsSdkInitializedCallback,
    GoogleMap.OnInfoWindowClickListener {

    private val binding by lazy {
        ActivityMapBinding.inflate(layoutInflater)
    }
    private lateinit var gMap: GoogleMap
    private lateinit var manager: LocationManager
    private val establishmentViewModel: EstablishmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, this)
        setContentView(binding.root)

        establishmentViewModel.establishments.observe(this){
            for (establishment in it){
                putNewMarker(establishment.lat, establishment.long, establishment.name, "${establishment.rating}", establishment.id!!)
                Log.d("MapActivity", "onCreate: ${establishment.lat} ${establishment.long}")
            }
        }

        binding.vmBackBTN.setOnClickListener{
            finish()
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.vmMapMP) as SupportMapFragment
        mapFragment.getMapAsync(this)

        manager = getSystemService(LOCATION_SERVICE) as LocationManager
        establishmentViewModel.getEstablishments()
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap
        gMap.setMyLocationEnabled(true)
        gMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))
        gMap.setOnInfoWindowClickListener(this)

        setInitialPos()
    }

    override fun onMapsSdkInitialized(renderer: MapsInitializer.Renderer) {
        when (renderer) {
            MapsInitializer.Renderer.LATEST -> Log.d("MapsDemo", "The latest version of the renderer is used.")
            MapsInitializer.Renderer.LEGACY -> Log.d("MapsDemo", "The legacy version of the renderer is used.")
        }
    }

    override fun onInfoWindowClick(marker: Marker) {
        val id = marker.tag
        Toast.makeText(this, "${id}", Toast.LENGTH_SHORT).show()
        //TODO:("Cambiar a la vista de establecimiento recuperando la información haciendo uso del ID guardado en el tag del marker")
    }

    @SuppressLint("MissingPermission")
    fun setInitialPos(){
        val pos = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (pos != null){
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(pos.latitude, pos.longitude), 15f
            ))
        }
    }

    fun putNewMarker(lat:Double, long:Double, title:String, rating:String, id:String) : Marker? {
        val pos = LatLng(lat, long)
        val marker = gMap.addMarker(
            MarkerOptions()
                .position(pos)
                .title(title)
                .snippet(rating)
        )
        marker?.setTag(id)

        //gMap.animateCamera(CameraUpdateFactory.newLatLng(pos))
        return marker
    }
}