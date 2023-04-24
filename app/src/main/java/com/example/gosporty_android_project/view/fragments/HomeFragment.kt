package com.example.gosporty_android_project.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gosporty_android_project.databinding.HomeFragmentBinding

class HomeFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:HomeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object{
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }

}