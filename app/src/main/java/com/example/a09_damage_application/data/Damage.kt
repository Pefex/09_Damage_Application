package com.example.a09_damage_application.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Damage (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val typeOfDamage: TypeOfDamage,
    val descriptionTitle: String,
    val description: String
)