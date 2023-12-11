package com.example.a09_damage_application.data.entities

import androidx.room.PrimaryKey

data class Owner(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val address: Address,
    val phoneNumber: Int, //?
    val phoneNumberMobil: Int,  //?
    val mailAdress: String,
    )
