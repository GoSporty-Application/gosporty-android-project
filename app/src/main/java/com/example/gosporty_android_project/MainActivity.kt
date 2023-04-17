package com.example.gosporty_android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import com.example.gosporty_android_project.R.layout.field

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToHome = findViewById<Button>(R.id.liLoginBTN)
        goToHome.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val homeLayout = inflater.inflate(field, null)
            setContentView(homeLayout)
        }
    }


}