package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivitySiteBinding

class SiteActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySiteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.sBackIB.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }

        binding.sGoToFieldIB1.setOnClickListener {
            startActivity(
                Intent(this, FieldActivity::class.java)
            )
        }

        binding.sGoToFieldIB2.setOnClickListener {
            startActivity(
                Intent(this, FieldActivity::class.java)
            )
        }
    }
}