package com.example.a09_damage_application.data.entities

import androidx.room.PrimaryKey
import com.example.a09_damage_application.data.enums.Type_Of_Owner

data class Ownership(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val typeOfOwner: Type_Of_Owner,
    val listOfOwners: MutableList<Owner>,


)
