package com.example.gosporty_android_project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.ActivityMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding by lazy {
        ActivityMapBinding.inflate(layoutInflater)
    }
    private lateinit var gMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.vmBackBTN.setOnClickListener{
            finish()
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.vmMapMP) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        gMap.addMarker(
            MarkerOptions().position(sydney).title("Marker in Sydney")
        )
        gMap.moveCamera(
            CameraUpdateFactory.newLatLng(sydney)
        )
    }
}