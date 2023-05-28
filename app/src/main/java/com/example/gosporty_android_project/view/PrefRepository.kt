package com.example.gosporty_android_project.view

import android.content.Context
import android.content.SharedPreferences
import com.example.gosporty_android_project.view.models.Establishment
import com.example.gosporty_android_project.view.models.User

class PrefRepository(val context: Context) {

    companion object {
        const val PREFERENCE_USER = "USER_PREFERENCE"
        const val PREFERENCE_ESTABLISHMENT = "ESTABLISHMENT_PREFERENCE"
        const val USER_ID = "USER_ID"
        const val USER_USERNAME = "USER_USERNAME"
        const val USER_NAME = "USER_NAME"
        const val USER_EMAIL = "USER_EMAIL"
        const val USER_LOCATION = "USER_LOCATION"
        const val USER_FAV_SPORT = "USER_FAV_SPORT"
        const val USER_PHOTO_URL = "USER_PHOTO_URL"
        const val USER_CELLPHONE = "USER_CELLPHONE"
        const val ESTABLISHMENT_ID = "ESTABLISHMENT_ID"
        const val ESTABLISHMENT_ADDRESS = "ESTABLISHMENT_ADDRESS"
        const val ESTABLISHMENT_JOURNEY = "ESTABLISHMENT_JOURNEY"
        const val ESTABLISHMENT_LAT = "ESTABLISHMENT_LAT"
        const val ESTABLISHMENT_LOGO = "ESTABLISHMENT_LOGO"
        const val ESTABLISHMENT_LONG = "ESTABLISHMENT_LONG"
        const val ESTABLISHMENT_NAME = "ESTABLISHMENT_NAME"
        const val ESTABLISHMENT_PHOTO = "ESTABLISHMENT_PHOTO"
        const val ESTABLISHMENT_RATING = "ESTABLISHMENT_RATING"
    }

    private val userPref: SharedPreferences = context.getSharedPreferences(PREFERENCE_USER, Context.MODE_PRIVATE)
    private val establishmentPref: SharedPreferences = context.getSharedPreferences(PREFERENCE_ESTABLISHMENT, Context.MODE_PRIVATE)

    private val userEditor = userPref.edit()
    private val establishmentEditor = establishmentPref.edit()

    fun setUser(user : User){
        val editor = userPref.edit()
        USER_ID.put(user.id!!, editor)
        USER_USERNAME.put(user.username, editor)
        USER_NAME.put(user.name, editor)
        USER_EMAIL.put(user.email, editor)
        USER_LOCATION.put(user.location, editor)
        USER_FAV_SPORT.put(user.favSport, editor)
        USER_PHOTO_URL.put(user.photoUrl, editor)
        USER_CELLPHONE.put(user.cellphone, editor)
    }

    fun getUser() : User {
        val pref = userPref
        return User(
            USER_ID.getString(pref),
            USER_USERNAME.getString(pref),
            USER_NAME.getString(pref),
            USER_EMAIL.getString(pref),
            USER_LOCATION.getString(pref),
            USER_FAV_SPORT.getString(pref),
            USER_PHOTO_URL.getString(pref),
            USER_CELLPHONE.getString(pref)
        )
    }

    fun setEstablishment(establishment: Establishment){
        val editor = establishmentPref.edit()
        ESTABLISHMENT_ID.put(establishment.id!!, editor)
        ESTABLISHMENT_ADDRESS.put(establishment.address, editor)
        ESTABLISHMENT_JOURNEY.put(establishment.journey, editor)
        ESTABLISHMENT_LAT.put(establishment.lat, editor)
        ESTABLISHMENT_LOGO.put(establishment.logo, editor)
        ESTABLISHMENT_LONG.put(establishment.long, editor)
        ESTABLISHMENT_NAME.put(establishment.name, editor)
        ESTABLISHMENT_PHOTO.put(establishment.photo, editor)
        ESTABLISHMENT_RATING.put(establishment.rating, editor)
    }

    fun getEstablishment() : Establishment {
        val pref = establishmentPref
        return Establishment(
            ESTABLISHMENT_ID.getString(pref),
            ESTABLISHMENT_ADDRESS.getString(pref),
            ESTABLISHMENT_JOURNEY.getString(pref),
            ESTABLISHMENT_LAT.getDouble(pref),
            ESTABLISHMENT_LOGO.getString(pref),
            ESTABLISHMENT_LONG.getDouble(pref),
            ESTABLISHMENT_NAME.getString(pref),
            ESTABLISHMENT_PHOTO.getString(pref),
            ESTABLISHMENT_RATING.getDouble(pref)
        )
    }

    private fun String.put(long: Long, editor: SharedPreferences.Editor) {
        editor.putLong(this, long)
        editor.commit()
    }

    private fun String.put(int: Int, editor: SharedPreferences.Editor) {
        editor.putInt(this, int)
        editor.commit()
    }

    private fun String.put(string: String, editor: SharedPreferences.Editor) {
        editor.putString(this, string)
        editor.commit()
    }

    private fun String.put(boolean: Boolean, editor: SharedPreferences.Editor) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.put(double: Double, editor: SharedPreferences.Editor) {
        editor.putLong(this, double.toLong())
        editor.commit()
    }

    private fun String.getLong(pref:SharedPreferences) = pref.getLong(this, 0)

    private fun String.getInt(pref:SharedPreferences) = pref.getInt(this, 0)

    private fun String.getString(pref:SharedPreferences) = pref.getString(this, "")!!

    private fun String.getBoolean(pref:SharedPreferences) = pref.getBoolean(this, false)

    private fun String.getDouble(pref:SharedPreferences) = pref.getLong(this, 0).toDouble()
}