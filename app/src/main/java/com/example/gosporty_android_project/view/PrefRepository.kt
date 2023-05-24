package com.example.gosporty_android_project.view

import android.content.Context
import android.content.SharedPreferences
import com.example.gosporty_android_project.view.models.User

class PrefRepository(val context: Context) {

    companion object {
        const val PREFERENCE_NAME = "USER_PREFERENCE"
        const val USER_ID = "USER_ID"
        const val USER_USERNAME = "USER_USERNAME"
        const val USER_NAME = "USER_NAME"
        const val USER_EMAIL = "USER_EMAIL"
        const val USER_LOCATION = "USER_LOCATION"
        const val USER_FAV_SPORT = "USER_FAV_SPORT"
        const val USER_PHOTO_URL = "USER_PHOTO_URL"
        const val USER_CELLPHONE = "USER_CELLPHONE"
    }

    private val pref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    private val editor = pref.edit()

    fun setUser(user : User){
        USER_ID.put(user.id!!)
        USER_USERNAME.put(user.username)
        USER_NAME.put(user.name)
        USER_EMAIL.put(user.email)
        USER_LOCATION.put(user.location)
        USER_FAV_SPORT.put(user.favSport)
        USER_PHOTO_URL.put(user.photoUrl)
        USER_CELLPHONE.put(user.cellphone)
    }

    fun getUser() : User {
        return User(
            USER_ID.getString(),
            USER_USERNAME.getString(),
            USER_NAME.getString(),
            USER_EMAIL.getString(),
            USER_LOCATION.getString(),
            USER_FAV_SPORT.getString(),
            USER_PHOTO_URL.getString(),
            USER_CELLPHONE.getString()
        )
    }

    private fun String.put(long: Long) {
        editor.putLong(this, long)
        editor.commit()
    }

    private fun String.put(int: Int) {
        editor.putInt(this, int)
        editor.commit()
    }

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.commit()
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.getLong() = pref.getLong(this, 0)

    private fun String.getInt() = pref.getInt(this, 0)

    private fun String.getString() = pref.getString(this, "")!!

    private fun String.getBoolean() = pref.getBoolean(this, false)
}