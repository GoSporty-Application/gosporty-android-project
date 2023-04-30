package com.example.gosporty_android_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.gosporty_android_project.databinding.ActivityMainBinding
import com.example.gosporty_android_project.view.viewmodels.AuthState
import com.example.gosporty_android_project.view.viewmodels.AuthViewModel
import com.example.gosporty_android_project.view.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val authViewModel: AuthViewModel by viewModels()
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        authViewModel.status.observe(this){
            when(it){
                AuthState.ERROR->{
                    Toast.makeText(this,"Hubo un error",Toast.LENGTH_SHORT).show()
                }
                AuthState.AUTHENTICATED->{
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                    )
                    finish()
                }
                AuthState.LOADING->{
                    Toast.makeText(this,"Cargando...",Toast.LENGTH_SHORT).show()
                }
            }
        }

        userViewModel.email.observe(this){
            authViewModel.logIn(
                it,
                binding.liPasswordET.text.toString()
            )
        }

        binding.liLoginBTN.setOnClickListener {
            val email = userViewModel.getLoginEmail(
                binding.liUsernameET.text.toString()
            )
        }

        binding.liRegisterBTN.setOnClickListener {
            val intent: Intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}