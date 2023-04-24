package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.suSaveBTN.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }
    }
}