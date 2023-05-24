package com.example.gosporty_android_project.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.gosporty_android_project.databinding.ProfileFragmentBinding
import com.example.gosporty_android_project.view.EditProfileActivity
import com.example.gosporty_android_project.view.PrefRepository
import com.example.gosporty_android_project.view.viewmodels.UserViewModel
import kotlin.math.log

class ProfileFragment : Fragment() {

    val userViewModel: UserViewModel by viewModels()
    val prefRepository: PrefRepository by lazy {
        PrefRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:ProfileFragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)

        val user = prefRepository.getUser()
        Log.d("ProfileFragment", "onCreateView: ${user}")
        binding.pUsernameTV.text = "@${user.username}"
        binding.pNameTV.text = user.name

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