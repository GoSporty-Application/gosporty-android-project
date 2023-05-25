package com.example.gosporty_android_project.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.databinding.HomeFragmentBinding
import com.example.gosporty_android_project.view.FieldActivity
import com.example.gosporty_android_project.view.HomeActivity
import com.example.gosporty_android_project.view.MapActivity
import com.example.gosporty_android_project.view.PermissionsActivity
import com.example.gosporty_android_project.view.adapters.AdvertiseAdapter

class HomeFragment : Fragment(){

    private val dialog = AvailableDialogFragment.newInstance()
    private lateinit var adapter: AdvertiseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:HomeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        /*
        binding.relativeLayoutFootball.setOnClickListener {
            val intent: Intent = Intent(this.context, FieldActivity::class.java)
            startActivity(intent)
        }
        binding.relativeLayoutVoleyball.setOnClickListener {
            dialog.show(childFragmentManager, "AvailableDialog")
        }
         */

        binding.hfMapBTN.setOnClickListener {
            val intent: Intent = Intent(this.context, PermissionsActivity::class.java)
            startActivity(intent)
        }

        adapter = AdvertiseAdapter()
        binding.hfAdRV.adapter = adapter
        binding.hfAdRV.layoutManager = LinearLayoutManager(this.context)
        binding.hfAdRV.setHasFixedSize(true)

        return binding.root
    }

    companion object{
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }
}