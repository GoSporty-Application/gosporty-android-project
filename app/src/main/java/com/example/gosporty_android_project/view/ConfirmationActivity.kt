package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivityConfirmationAndPaymentBinding

class ConfirmationActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityConfirmationAndPaymentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.capBackIB.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }

        binding.capSiteLogoIV.setOnClickListener {
            startActivity(
                Intent(this, SiteActivity::class.java)
            )
        }

        binding.capSubmitBTN.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }
    }
}