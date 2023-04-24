package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.epHomeIB.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }

        binding.epBookingsIB.setOnClickListener {
            startActivity(
                Intent(this, BookingsActivity::class.java)
            )
        }

        binding.epSaveBTN.setOnClickListener {
//            startActivity(
//                Intent(this, PROFILE)
//            )
        }

        binding.epProfileIB.setOnClickListener {
//            startActivity(
//                Intent(this, PROFILE)
//            )
        }
    }
}