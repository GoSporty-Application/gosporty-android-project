package com.example.gosporty_android_project.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.ActivityHomeBinding
import com.example.gosporty_android_project.view.fragments.BookingFragment
import com.example.gosporty_android_project.view.fragments.HomeFragment
import com.example.gosporty_android_project.view.fragments.ProfileFragment
import com.example.gosporty_android_project.view.viewmodels.AuthState
import com.example.gosporty_android_project.view.viewmodels.AuthViewModel
import com.example.gosporty_android_project.view.viewmodels.UserViewModel

class HomeActivity : AppCompatActivity() {

    private val binding:ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val home = HomeFragment.newInstance()
    private val booking = BookingFragment.newInstance()
    private val profile = ProfileFragment.newInstance()
    private val authViewModel: AuthViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(home)
        setContentView(binding.root)

        authViewModel.status.observe(this){
            when(it){
                AuthState.NO_AUTH->{
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    finish()
                }
                AuthState.AUTHENTICATED->{

                }
                AuthState.LOADING->{
                    Toast.makeText(this,"Cargando...",Toast.LENGTH_SHORT).show()
                }
            }
        }

        authViewModel.getAuthStatus()

        binding.hHomeBTN.setOnClickListener{
            showFragment(home)
        }
        binding.hMenuBTN.setOnClickListener{
            showFragment(booking)
        }
        binding.hProfileBTN.setOnClickListener{
            showFragment(profile)
        }
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHomeContainer, fragment).commit()
    }

    fun saveUser(){
        val sp = getSharedPreferences("user", MODE_PRIVATE);
    }
}