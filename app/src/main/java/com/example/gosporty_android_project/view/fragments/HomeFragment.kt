package com.example.gosporty_android_project.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.HomeFragmentBinding
import com.example.gosporty_android_project.view.FieldActivity
import com.example.gosporty_android_project.view.HomeActivity

class HomeFragment : Fragment(){

    private val dialog = AvailableDialogFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:HomeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.relativeLayoutFootball.setOnClickListener {
            val intent: Intent = Intent(this.context, FieldActivity::class.java)
            startActivity(intent)
        }
        binding.relativeLayoutVoleyball.setOnClickListener {
            dialog.show(childFragmentManager, "AvailableDialog")
        }
        return binding.root
    }

    companion object{
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }
}