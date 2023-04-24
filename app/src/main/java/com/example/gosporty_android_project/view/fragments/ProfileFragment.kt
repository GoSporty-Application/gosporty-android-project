package com.example.gosporty_android_project.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.ActivityHomeBinding
import com.example.gosporty_android_project.databinding.ProfileFragmentBinding
import com.example.gosporty_android_project.view.EditProfileActivity
import com.example.gosporty_android_project.view.HomeActivity

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:ProfileFragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)
        binding.pEditProfileLayoutLL.setOnClickListener {
            val intent: Intent = Intent(this.context, EditProfileActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object{
        fun newInstance():ProfileFragment{
            return ProfileFragment()
        }
    }

}