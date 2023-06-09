package com.example.gosporty_android_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.example.gosporty_android_project.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }
    val prefRepository: PrefRepository by lazy {
        PrefRepository(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val user = prefRepository.getUser()
        Log.d("ProfileFragment", "onCreateView: ${user}")
        binding.epHeaderUserTV.text = "@${user.username}"
        binding.epNombreTV.text = user.name
        binding.epNameET.text = Editable.Factory.getInstance().newEditable(user.name)
        binding.epUsernameET.text = Editable.Factory.getInstance().newEditable(user.username)

        binding.epSoccerCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.epVoleyCB.isChecked = false
                binding.epBasketCB.isChecked = false
            }
        }
        binding.epVoleyCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.epSoccerCB.isChecked = false
                binding.epBasketCB.isChecked = false
            }
        }
        binding.epBasketCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.epVoleyCB.isChecked = false
                binding.epSoccerCB.isChecked = false
            }
        }


        binding.epSaveBTN.setOnClickListener {
            val intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}