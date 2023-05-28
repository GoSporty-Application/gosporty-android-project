package com.example.gosporty_android_project.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gosporty_android_project.databinding.TableRowBinding

class TableRowViewHolder(root: View) : RecyclerView.ViewHolder(root) {

    val binding = TableRowBinding.bind(root)
    val principalTV = binding.trPrincipalTV
    val secondaryTV = binding.trSecondaryTV
    val buttonIB = binding.trButtonIB
    val context = root.context

}