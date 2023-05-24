package com.example.gosporty_android_project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosporty_android_project.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMapBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.vmBackBTN.setOnClickListener{
            finish()
        }
    }
}