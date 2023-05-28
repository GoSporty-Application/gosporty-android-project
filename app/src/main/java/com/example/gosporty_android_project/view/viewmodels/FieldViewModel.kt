package com.example.gosporty_android_project.view.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosporty_android_project.view.models.Field
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FieldViewModel() : ViewModel() {

    private val _fields: MutableLiveData<List<Field>> = MutableLiveData()
    val fields: MutableLiveData<List<Field>> = _fields

    fun getEstablishments(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = Firebase.firestore.collection("establishments").document(
                    id
                ).collection("fields").get().await()
                val fields = res.toObjects(Field::class.java)
                withContext(Dispatchers.Main){
                    _fields.value = fields
                }
            } catch (e: Exception) {
                Log.d("EstablishmentViewModel", "getEstablishments: ${e.message}")
            }
        }
    }

}