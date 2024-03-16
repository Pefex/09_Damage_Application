package com.example.a09_damage_application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OwnerList(
    @PrimaryKey(autoGenerate = true) //
    val  id: Int? = null,

)
