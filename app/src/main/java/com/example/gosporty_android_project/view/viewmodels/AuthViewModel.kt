package com.example.gosporty_android_project.view.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosporty_android_project.view.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthViewModel:ViewModel() {

    val status: MutableLiveData<Int> = MutableLiveData()

    fun getAuthStatus() {
        Log.d("AuthViewModel", "getAuthStatus: consultando estado de autenticación...")
        if(Firebase.auth.currentUser == null) {
            status.value = AuthState.NO_AUTH
        } else {
            status.value = AuthState.AUTHENTICATED
        }
    }

    fun logIn(email:String, password:String) {
        status.value = AuthState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Firebase.auth.signInWithEmailAndPassword(
                    email,
                    password
                ).await()
                withContext(Dispatchers.Main) {
                    status.value = AuthState.AUTHENTICATED
                }
            } catch (e:Exception) {
                Log.d("AuthViewModel", "logIn: ${e.message}")
                withContext(Dispatchers.Main) {
                    status.value = AuthState.ERROR
                }
            }
        }
    }

    fun signUp(user: User, password:String) {
        status.value = AuthState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var response = Firebase.auth.createUserWithEmailAndPassword(
                    user.email,
                    password
                ).await()
                user.id = response.user!!.uid
                Firebase.firestore
                    .collection("users")
                    .document(user.id!!)
                    .set(user).await()
                withContext(Dispatchers.Main) {
                    status.value = AuthState.AUTHENTICATED
                }
            } catch (e:Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    status.value = AuthState.ERROR
                }
            }
        }
    }

    fun loginWithGoogle(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Firebase.auth
            } catch (e:Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    status.value = AuthState.ERROR
                }
            }
        }
    }


}

object AuthState {
    const val NO_AUTH = 0;
    const val LOADING = 1;
    const val AUTHENTICATED = 2;
    const val ERROR = -1;
}