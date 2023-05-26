package com.example.gosporty_android_project.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.MapCustomInfoBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(mContext: Context) : GoogleMap.InfoWindowAdapter {

    private var mWindow : View = LayoutInflater.from(mContext).inflate(R.layout.map_custom_info, null)

    fun renderWindowText(marker : Marker){
        val title = marker.title
        val snippet = marker.snippet

        mWindow.findViewById<TextView>(R.id.mciTitleTV).text = title
        mWindow.findViewById<TextView>(R.id.mciRatingTV).text = snippet
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