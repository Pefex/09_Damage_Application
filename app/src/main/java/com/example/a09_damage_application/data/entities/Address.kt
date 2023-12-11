package com.example.a09_damage_application.data.entities

import androidx.room.PrimaryKey

data class Address(
    @PrimaryKey(autoGenerate = true) //
    val  id: Int? = null,
    val street: String,
    val streetNumber: String,
    val postalCode: Int,
    val city: String,
    val country: String,
)
