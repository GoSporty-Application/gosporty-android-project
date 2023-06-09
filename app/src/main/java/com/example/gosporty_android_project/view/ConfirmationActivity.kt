package com.example.gosporty_android_project.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gosporty_android_project.databinding.ActivityConfirmationAndPaymentBinding
import com.example.gosporty_android_project.view.models.Reservation
import com.example.gosporty_android_project.view.viewmodels.EstablishmentViewModel
import com.example.gosporty_android_project.view.viewmodels.FieldViewModel
import java.util.UUID

class ConfirmationActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityConfirmationAndPaymentBinding.inflate(layoutInflater)
    }
    val prefRepository by lazy {
        PrefRepository(this)
    }
    val fieldViewModel : FieldViewModel by viewModels()
    private var fieldId : String = ""
    private var day : Int = 0
    private var month : Int = 0
    private var year : Int = 0
    private var start : Int = 0
    private var end : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.capBackIB.setOnClickListener {
            finish()
        }

        binding.capSiteLogoIV.setOnClickListener {
            finish()
        }

        binding.capSubmitBTN.setOnClickListener {
            val establishment = prefRepository.getEstablishment()
            val user = prefRepository.getUser()
            val reserve = Reservation(null, establishment.id!!, fieldId, start, end, day, month, year, user.id!!)
            fieldViewModel.reserve(fieldId,establishment.id!!, reserve)
        }

        fieldViewModel.field.observe(this){
            val establishment = prefRepository.getEstablishment()
            day = intent.getIntExtra("day", 0)
            month = intent.getIntExtra("month", 0)
            year = intent.getIntExtra("year", 0)
            val reservations = intent.getIntegerArrayListExtra("reservations")
            start = reservations!!.min()
            end = reservations.max()!! + 1
            binding.capSiteNameTV.text = establishment.name
            binding.capFieldInfoTV.text = it.name
            binding.capSiteNameInfoTV.text = establishment.name
            binding.capFieldNameTV.text = it.name
            binding.capSiteAddressTV.text = establishment.address
            binding.capDateTV.text = "$day/$month/$year"
            binding.capHourTV.text = "${start}:00 - ${end}:00"
        }

        fieldViewModel.status.observe(this){
            when (it) {
                "success" -> {
                    Toast.makeText(this, "Reserva exitosa", Toast.LENGTH_SHORT).show()
                    finish()
                }
                "error" -> {
                    Toast.makeText(this, "Error al reservar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fieldId = intent.getStringExtra("fieldId")!!
        fieldViewModel.getField(fieldId)
    }
}