package com.example.gosporty_android_project.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gosporty_android_project.databinding.ActivitySiteBinding
import com.example.gosporty_android_project.view.adapters.TableRowAdapter
import com.example.gosporty_android_project.view.models.Establishment
import com.example.gosporty_android_project.view.models.TableRow
import com.example.gosporty_android_project.view.viewmodels.EstablishmentViewModel
import com.example.gosporty_android_project.view.viewmodels.FieldViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SiteActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySiteBinding.inflate(layoutInflater)
    }
    val establishmentViewModel: EstablishmentViewModel by viewModels()
    val fieldViewModel: FieldViewModel by viewModels()
    val prefRepository by lazy {
        PrefRepository(this)
    }
    private lateinit var adapter: TableRowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        establishmentViewModel.establishment.observe(this) {
            prefRepository.setEstablishment(it)
            binding.sSiteNameTV.text = it.name
            binding.sInfoNameTV.text = it.name
            binding.sAddressTV.text = it.address
            binding.sGradeTV.text = it.rating.toString()
            binding.sSiteScheduleTV.text = it.journey
            dowloadImages(it.logo, it.photo, it.id)
        }

        adapter = TableRowAdapter()
        binding.sFieldsRV.adapter = adapter
        binding.sFieldsRV.layoutManager = LinearLayoutManager(this)
        binding.sFieldsRV.setHasFixedSize(true)

        fieldViewModel.fields.observe(this) {
            for (field in it){
                if(field.available){
                    adapter.addRow(TableRow(field.id,field.name,field.size,"arrow"))
                }else {
                    adapter.addRow(TableRow(field.id,field.name,field.size,"disabled"))
                }
            }
        }

        val text = intent.getStringExtra("establishmentId")

        establishmentViewModel.getEstablishment(text!!)

        fieldViewModel.getFields(prefRepository.getEstablishment().id!!)

        binding.sBackIB.setOnClickListener {
            finish()
        }
    }

    fun dowloadImages(logoID: String?, photoID: String?, idSite: String?){
        Log.e(">>>>>> LOGO -> ", logoID!!)
        Log.e(">>>>>> photo ->", photoID!!)
        Log.e(">>>>>> id establishment ->", idSite!!)
        Firebase.storage.getReference().child("establishments").child(idSite!!).child(photoID!!+".jpg").downloadUrl.addOnSuccessListener {
            Log.e(">>>>>>","Entre a descargar PHOTO")
            Glide.with(binding.sSitePhotoIV).load(it).into(binding.sSitePhotoIV)
        }
        Firebase.storage.getReference().child("establishments").child(idSite!!).child(logoID!!+".png").downloadUrl.addOnSuccessListener {
            Log.e(">>>>>>","Entre a descargar LOGO")
            Glide.with(binding.sSiteLogoIV).load(it).into(binding.sSiteLogoIV)
        }
    }
}