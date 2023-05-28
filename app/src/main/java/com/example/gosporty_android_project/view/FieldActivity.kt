package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivityFieldBinding
import com.example.gosporty_android_project.view.viewmodels.FieldViewModel

class FieldActivity : AppCompatActivity() {

    val fieldViewModel : FieldViewModel by viewModels()
    val prefRepository by lazy {
        PrefRepository(this)
    }

    val binding by lazy {
        ActivityFieldBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fBackIB.setOnClickListener {
            finish()
        }

        fieldViewModel.field.observe(this) {
            val establishment = prefRepository.getEstablishment()
            binding.fSiteNameTV.text = establishment.name
            binding.fSiteNameCardTV.text = establishment.name
            binding.fAddressCardTV.text = establishment.address
            binding.fFieldNameTV.text = it.name
            binding.fDescriptionCardTV.text = it.name
            binding.fGradeCardTV.text = establishment.rating.toString()
        }

        var id = intent.getStringExtra("fieldId")
        fieldViewModel.getField(id!!)

    }
}