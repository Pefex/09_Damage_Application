package com.example.a09_damage_application.data.entities

import androidx.compose.runtime.MutableState
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a09_damage_application.data.enums.TypeOfGender
import com.example.a09_damage_application.data.enums.TypeOfTitle

@Entity
data class Name(
    @PrimaryKey(autoGenerate = true) //
    val id: Int? = null,
    val typeOfGender: TypeOfGender,
    val typOfTitle: TypeOfTitle,
    val firstName: String,
    val lastName: String,



    )