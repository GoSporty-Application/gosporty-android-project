package com.example.gosporty_android_project.view.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosporty_android_project.view.models.Establishment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class EstablishmentViewModel(): ViewModel() {

    private val _establishments: MutableLiveData<List<Establishment>> = MutableLiveData()
    val establishments: MutableLiveData<List<Establishment>> = _establishments

    fun getEstablishments() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = Firebase.firestore.collection("establishments").get().await()
                val ests = res.toObjects(Establishment::class.java)
                withContext(Dispatchers.Main) {
                    _establishments.value = ests
                }
            } catch (e: Exception) {
                Log.d("EstablishmentViewModel", "getEstablishments: ${e.message}")
            }
        }
    }

}