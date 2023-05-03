package com.example.gosporty_android_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.gosporty_android_project.databinding.ActivitySignUpBinding
import com.example.gosporty_android_project.view.models.User
import com.example.gosporty_android_project.view.viewmodels.AuthState
import com.example.gosporty_android_project.view.viewmodels.AuthViewModel

class SignUpActivity : AppCompatActivity() {


    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    val viewmodel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewmodel.status.observe(this){
            when(it){
                AuthState.ERROR->{
                    Toast.makeText(this,"Hubo un error",Toast.LENGTH_SHORT).show()
                }
                AuthState.LOADING->{
                    Toast.makeText(this,"Cargando...",Toast.LENGTH_SHORT).show()
                }
                AuthState.AUTHENTICATED->{
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                    )
                    finish()
                }
            }
        }

        binding.suSaveBTN.setOnClickListener {
            val name = binding.suNameET.text.toString()
            val username = binding.suUsernameET.text.toString()
            val email = binding.suEmailET.text.toString()
            val password = binding.suPasswordET.text.toString()
            val user = User(null,username,name,email)
            viewmodel.signUp(user,password)
        }
    }
}