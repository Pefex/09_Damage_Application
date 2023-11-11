package com.example.a09_damage_application.data

class Damage (typeOfDemage: TypeOfDamage , descriptionTitle: String, description: String){
    var typeOfDemage: TypeOfDamage = typeOfDemage
    var descriptionTitle: String = descriptionTitle
    var description: String = description


    fun Get_description(): String {
        return description
    }


}