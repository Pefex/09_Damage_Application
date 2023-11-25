package com.example.a09_damage_application.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "damages")
data class Damage(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var typeOfDemage: TypeOfDamage,
    var descriptionTitle: String,
    var description: String

)





/*
class Damage (typeOfDemage: TypeOfDamage , descriptionTitle: String, description: String){
    var typeOfDemage: TypeOfDamage = typeOfDemage
    var descriptionTitle: String = descriptionTitle
    var description: String = description

/*
    fun Get_description(): String {
        return description
    }


}*/