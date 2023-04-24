package com.example.gosporty_android_project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.ActivityHomeBinding
import com.example.gosporty_android_project.view.fragments.BookingFragment
import com.example.gosporty_android_project.view.fragments.HomeFragment
import com.example.gosporty_android_project.view.fragments.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private val binding:ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val home = HomeFragment.newInstance()
    private val booking = BookingFragment.newInstance()
    private val profile = ProfileFragment.newInstance()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(home)
        setContentView(binding.root)

        binding.buttonHome.setOnClickListener{
            showFragment(home)
        }
        binding.buttonBooking.setOnClickListener{
            showFragment(booking)
        }
        binding.buttonProfile.setOnClickListener{
            showFragment(profile)
        }
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHomeContainer, fragment).commit()
    }
}