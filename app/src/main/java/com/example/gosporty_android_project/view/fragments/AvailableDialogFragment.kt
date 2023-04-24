package com.example.gosporty_android_project.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.gosporty_android_project.databinding.AvailableDialogFragmentBinding

class AvailableDialogFragment : DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:AvailableDialogFragmentBinding = AvailableDialogFragmentBinding.inflate(inflater, container, false)
        binding.hModalBtn.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    companion object{
        fun newInstance():AvailableDialogFragment{
            return AvailableDialogFragment()
        }
    }

}