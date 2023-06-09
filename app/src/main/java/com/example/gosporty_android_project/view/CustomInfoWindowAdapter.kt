package com.example.gosporty_android_project.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.MapCustomInfoBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class CustomInfoWindowAdapter(mContext: Context) : GoogleMap.InfoWindowAdapter {

    private var mWindow : View = LayoutInflater.from(mContext).inflate(R.layout.map_custom_info, null)
    private var mContext = mContext

    fun renderWindowText(marker : Marker){
        val title = marker.title
        val snippet = marker.snippet
        val rating = snippet!!.split("-")[0]
        val photo = snippet!!.split("-")[1]

        mWindow.findViewById<TextView>(R.id.mciTitleTV).text = title
        mWindow.findViewById<TextView>(R.id.mciRatingTV).text = rating
        Log.d("CustomMap", "$photo-${marker.tag}")
        Firebase.storage.reference.child("establishments").child(marker.tag as String).child("$photo.jpg").downloadUrl.addOnSuccessListener {
            Glide.with(mWindow.findViewById<ImageView>(R.id.mciImageIV)).load(it).into(mWindow.findViewById(R.id.mciImageIV))
        }
    }

    override fun getInfoContents(marker: Marker): View? {
        renderWindowText(marker)
        return mWindow
    }

    override fun getInfoWindow(marker: Marker): View? {
        renderWindowText(marker)
        return mWindow
    }

}