package com.example.a09_damage_application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.a09_damage_application.data.enums.Type_Of_Level


@Entity
data class Localization(
    @PrimaryKey(autoGenerate = true)
    val localizationId: Int? = null,
    val name: String,
    val rootLocalizationId: Int? = null,
    val position: Int = 0,
    val numberOfLevel: Int = 0
    )
