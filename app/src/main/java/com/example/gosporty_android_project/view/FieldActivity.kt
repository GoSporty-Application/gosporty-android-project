package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivityFieldBinding

class FieldActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFieldBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fBackIB.setOnClickListener {
            startActivity(
                Intent(this, SiteActivity::class.java)
            )
        }

        binding.fBookedB.setOnClickListener {
            startActivity(
                Intent(this, ConfirmationActivity::class.java)
            )
        }

        binding.fOpenIB.setOnClickListener {
            startActivity(
                Intent(this, ConfirmationActivity::class.java)
            )
        }
    }
}