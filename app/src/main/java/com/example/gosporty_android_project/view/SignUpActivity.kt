package com.example.gosporty_android_project.view

import android.content.Intent
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosporty_android_project.R
=======
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
>>>>>>> 22d7f3bf882ca80a1162a3d8e9fe57f652a1784e
import com.example.gosporty_android_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

<<<<<<< HEAD
    private val binding by lazy {
=======
    val binding by lazy {
>>>>>>> 22d7f3bf882ca80a1162a3d8e9fe57f652a1784e
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.suSaveBTN.setOnClickListener {
<<<<<<< HEAD
            val intent: Intent = Intent (this, HomeActivity::class.java)
            startActivity(intent)
        }

=======
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }
>>>>>>> 22d7f3bf882ca80a1162a3d8e9fe57f652a1784e
    }
}