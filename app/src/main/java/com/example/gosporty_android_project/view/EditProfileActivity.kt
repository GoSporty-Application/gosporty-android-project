package com.example.gosporty_android_project.view

import android.content.Intent
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private val binding by lazy {
=======
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    val binding by lazy {
>>>>>>> 22d7f3bf882ca80a1162a3d8e9fe57f652a1784e
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

<<<<<<< HEAD
        binding.epSaveBTN.setOnClickListener {
            val intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

=======
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
>>>>>>> 22d7f3bf882ca80a1162a3d8e9fe57f652a1784e
    }
}