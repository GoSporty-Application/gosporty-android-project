package com.example.gosporty_android_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosporty_android_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       binding.liLoginBTN.setOnClickListener{
           var username = binding.liNameET.text.toString()
           var pass = binding.liUsernameET.text.toString()
           var text = "${username} : ${pass}"
           val intent: Intent = Intent(this, HomeActivity::class.java)
           startActivity(intent)
       }
    }


}