package com.example.gosporty_android_project.view.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosporty_android_project.view.models.Reservation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ReservationViewModel: ViewModel() {

    private val _reservations: MutableLiveData<List<Reservation>> = MutableLiveData()
    val reservations: MutableLiveData<List<Reservation>> = _reservations

    fun getSchedules(idEstablishment: String, idField: String, day:Int, month:Int, year:Int) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = Firebase.firestore.collection("establishments").document(
                    idEstablishment
                ).collection("fields").document(idField).collection("reservations").
                    whereEqualTo("day",day).whereEqualTo("month",month).whereEqualTo("year",year).get().await()
                val reservations = res.toObjects(Reservation::class.java)
                withContext(Dispatchers.Main){
                    _reservations.value = reservations
                }
                Log.d("ScheduleViewModel", "establishment: ${idEstablishment}, field: ${idField}")
            } catch (e: Exception) {
                Log.d("ScheduleViewModel", "getSchedules: ${e.message}")
            }
        }
    }

    fun getSchedulesByUser(idUser: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = Firebase.firestore.collectionGroup("reservations").get().await()
                val reservations = res.toObjects(Reservation::class.java)
                val filtered = arrayListOf<Reservation>()
                for (reservation in reservations){
                    if(reservation.owner.equals(idUser)){
                        filtered.add(reservation)
                    }
                }
                withContext(Dispatchers.Main){
                    _reservations.value = filtered
                }
                Log.d("ScheduleViewModel", "user: ${idUser}")
            } catch (e: Exception) {
                Log.d("ScheduleViewModel", "getSchedules: ${e.message}")
            }
        }
    }

}