package com.example.a09_damage_application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class BuildingElement(
    @PrimaryKey(autoGenerate = true)
    val buildingElementId: Int? = null,
    val name: String,
    val rootLocalizationId: Int? = null,
    val categories: List<String>,  //Brandschutztür-Überprüfung: mechanische Überüprüfung, elektrische Überprüfung
    val checkIds: List<Int>,  //Die Überprüfungen zu diesem Bauelement - jede Überprüfung/Begehung hat eine bestimmte Id
    val damageIds: List<Int>,
    val localizationId: Int,
    val maintenanceProtocolIds: List<Int>


    )


