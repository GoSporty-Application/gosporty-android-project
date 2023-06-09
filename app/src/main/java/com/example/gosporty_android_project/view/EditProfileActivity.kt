package com.example.gosporty_android_project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.gosporty_android_project.databinding.ActivityEditProfileBinding
import com.example.gosporty_android_project.view.models.User
import com.example.gosporty_android_project.view.viewmodels.UserViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class EditProfileActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }
    val prefRepository: PrefRepository by lazy {
        PrefRepository(this)
    }

    val userViewModel: UserViewModel by viewModels()

    private lateinit var galleryLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val user = prefRepository.getUser()
        Log.d("ProfileFragment", "onCreateView: ${user}")
        binding.epHeaderUserTV.text = "@${user.username}"
        binding.epNombreTV.text = user.name
        binding.epNameET.text = Editable.Factory.getInstance().newEditable(user.name)
        binding.epUsernameET.text = Editable.Factory.getInstance().newEditable(user.username)
        binding.epCellET.text = Editable.Factory.getInstance().newEditable(user.cellphone)
        binding.epLocalET.text = Editable.Factory.getInstance().newEditable(user.location)
        Log.d("EDITPROFILE", "${user.id}- - - - - -${user.photoUrl}")

        if(user.photoUrl!=""){
            Firebase.storage.getReference().child("users").child(user.id!!).child(user.photoUrl).downloadUrl.addOnSuccessListener {
                Glide.with(binding.epPhotoIV).load(it).into(binding.epPhotoIV)
            }
        }

        galleryLauncher = registerForActivityResult(StartActivityForResult(),::onGalleryResult)

        binding.epEditPhotoIB.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }
        var favoriteSport: String = ""
        binding.epSoccerCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                favoriteSport = "Fútbol"
                binding.epVoleyCB.isChecked = false
                binding.epBasketCB.isChecked = false
            }
        }
        binding.epVoleyCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                favoriteSport = "Voleyball"
                binding.epSoccerCB.isChecked = false
                binding.epBasketCB.isChecked = false
            }
        }
        binding.epBasketCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                favoriteSport = "Baloncesto"
                binding.epVoleyCB.isChecked = false
                binding.epSoccerCB.isChecked = false
            }
        }


        binding.epSaveBTN.setOnClickListener {
            userViewModel.editUser(user.id!!, User(user.id, binding.epUsernameET.text.toString(),
                binding.epNameET.text.toString(),user.email,
                binding.epLocalET.text.toString(),favoriteSport, user.photoUrl, binding.epCellET.text.toString()))
            prefRepository.setUser(User(user.id, binding.epUsernameET.text.toString(),
                binding.epNameET.text.toString(),user.email,
                binding.epLocalET.text.toString(),favoriteSport, user.photoUrl, binding.epCellET.text.toString()))
            finish()
        }
    }

    fun onGalleryResult(result : ActivityResult){
        if(result.resultCode== RESULT_OK){
            val uri = result.data?.data
            binding.epPhotoIV.setImageURI(uri)
            val filename = UUID.randomUUID().toString()
            Firebase.storage.getReference().child("users").child(prefRepository.getUser().id!!).child(filename).putFile(uri!!)
            Firebase.firestore.collection("users").document(prefRepository.getUser().id!!).update("photoUrl", filename)
            val user: User = prefRepository.getUser()
            prefRepository.setUser(User(user.id, user.username, user.name, user.email, user.location, user.favSport, filename, user.cellphone))
        }
    }
}