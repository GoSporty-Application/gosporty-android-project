package com.example.gosporty_android_project.view.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.example.gosporty_android_project.view.PrefRepository
import com.example.gosporty_android_project.view.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
class UserViewModel(): ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: MutableLiveData<User> = _user
    private val _email: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = _email


    fun getLoginEmail(username:String) {
        viewModelScope.launch(Dispatchers.IO){
            try{
                val res = Firebase.firestore.collection("users").whereEqualTo(
                    "username",
                    username
                ).get().await().documents.get(0).get("email").toString()
                withContext(Dispatchers.Main){
                    _email.value = res
                }
            } catch (e:Exception) {
                Log.d("UserViewModel", "getLoginEmail: ${e.message}")
                withContext(Dispatchers.Main){
                    _email.value = "ERROR"
                }
            }
        }
    }

    fun getUser(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val res = Firebase.firestore.collection("users").document(
                    Firebase.auth.currentUser!!.uid
                ).get().await()
                val me = res.toObject(User::class.java)
                withContext(Dispatchers.Main) {
                    _user.value = me!!
                }
            }catch(e:Exception){
                e.printStackTrace()
            }
        }
    }
}