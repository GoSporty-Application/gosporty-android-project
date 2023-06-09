package com.example.gosporty_android_project.view.models

data class Reservation(
    val id: String? = "",
    val idEstablishment: String = "",
    val idField: String = "",
    val start: Int = 0,
    val end: Int = 0,
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
    val owner: String = "",
)
