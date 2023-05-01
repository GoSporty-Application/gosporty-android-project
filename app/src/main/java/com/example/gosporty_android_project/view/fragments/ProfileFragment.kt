package com.example.gosporty_android_project.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.ActivityHomeBinding
import com.example.gosporty_android_project.databinding.ProfileFragmentBinding
import com.example.gosporty_android_project.view.EditProfileActivity
import com.example.gosporty_android_project.view.HomeActivity
import com.example.gosporty_android_project.view.viewmodels.UserViewModel

class ProfileFragment : Fragment() {

    val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:ProfileFragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)

        userViewModel.user.observe(viewLifecycleOwner){
            binding.pNameTV.text = it.nombre
            binding.pUsernameTV.text = "@${it.username}"
        }

        binding.pEditProfileLayoutLL.setOnClickListener {
            val intent: Intent = Intent(this.context, EditProfileActivity::class.java)
            startActivity(intent)

        }
        userViewModel.getUser()
        return binding.root
    }

    companion object{
        fun newInstance():ProfileFragment{
            return ProfileFragment()
        }
    }

}