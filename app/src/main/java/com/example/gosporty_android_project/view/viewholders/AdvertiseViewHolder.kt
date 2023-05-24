package com.example.gosporty_android_project.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gosporty_android_project.databinding.AdCardBinding

class AdvertiseViewHolder(root: View) : ViewHolder(root) {

    val binding = AdCardBinding.bind(root)
    val cardCV = binding.adcCardCV
    val imageCV = binding.adcImageCV
    val titleTV = binding.adcTitleTV
    val fieldTV = binding.adcFieldTV
    val discountTV = binding.adcDiscountTV
    val context = root.context

}