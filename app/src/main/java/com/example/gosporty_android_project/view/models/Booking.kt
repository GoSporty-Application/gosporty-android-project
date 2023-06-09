package com.example.gosporty_android_project.view.models

data class Booking (
    val type:String="",
    val site:String="",
    val field:String="",
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
    val start: Int = 0,
    val end: Int = 0,
    val photo: String = ""
)