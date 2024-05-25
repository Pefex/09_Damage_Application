package com.example.a09_damage_application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class BuildingElement(
    @PrimaryKey(autoGenerate = true)
    val buildingElementId: Int? = null,
    val name: String,
    val rootLocalizationId: Int? = null,
    val inspectionType: String?,  //Brandschutztür-Überprüfung: mechanische Überüprüfung, elektrische Überprüfung
    val checkIds: Int? = null,  //Die Überprüfungen zu diesem Bauelement - jede Überprüfung/Begehung hat eine bestimmte Id
    val damageIds: Int? = null,
    val rootBuildingElementId: Int?  = null, //Wenn es ein Unter-Bauelemnt ist.
    val maintenanceProtocolIds: Int? = null, //Hier kann man Wartungsprotokolle anhängen


    )


