package com.example.gosporty_android_project.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gosporty_android_project.databinding.BookingCardBinding

class BookingsViewHolder(root: View) : RecyclerView.ViewHolder(root){

    private val binding = BookingCardBinding.bind(root)
    val typeText = binding.bTypeTV
    val siteText = binding.bSiteTV
    val fieldText = binding.bFieldTV
    val date = binding.bDateTV
    val photo = binding.bcPhotoIV
    val hour = binding.bHourTV

}