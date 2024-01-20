package com.example.a09_damage_application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Address(
    @PrimaryKey(autoGenerate = true) //
    val id: Int? = null,
    val street: String,
    val streetNumber: String,
    val postalCode: String,
    val city: String,
    val country: String,
)
