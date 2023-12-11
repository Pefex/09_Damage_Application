package com.example.a09_damage_application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a09_damage_application.data.enums.TypeOfDamage

@Entity  //
data class Damage ( // Das ist die "Speisekarte".
    @PrimaryKey(autoGenerate = true) //
    val  id: Int? = null,
    val typeOfDamage: TypeOfDamage,
    val descriptionTitle: String,
    val description: String
)
