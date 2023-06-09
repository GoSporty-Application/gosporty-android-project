package com.example.gosporty_android_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.example.gosporty_android_project.databinding.ActivityEditProfileBinding
import com.example.gosporty_android_project.view.PrefRepository
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

        binding.epSaveBTN.setOnClickListener {
            val intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}