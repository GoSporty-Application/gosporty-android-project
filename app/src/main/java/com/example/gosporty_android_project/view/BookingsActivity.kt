package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivityBookingsBinding

class BookingsActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityBookingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bHomeIB.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }

        binding.bBookingsIB.setOnClickListener {
            startActivity(
                Intent(this, BookingsActivity::class.java)
            )
        }

        binding.bProfileIB.setOnClickListener {
//            startActivity(
//                Intent(this, PROFILE)
//            )
        }
    }
}