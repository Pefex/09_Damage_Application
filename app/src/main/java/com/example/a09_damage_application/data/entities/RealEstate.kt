package com.example.a09_damage_application.data.entities

import androidx.room.PrimaryKey

data class RealEstate(
    @PrimaryKey(autoGenerate = true) // Das ist die "Speisekarte".
    val  id: Int? = null,
    val address: Address,
    val ownership: Ownership

)
