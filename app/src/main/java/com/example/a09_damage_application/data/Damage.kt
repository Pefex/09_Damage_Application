package com.example.a09_damage_application.data

class Damage (typeOfDemage: String  , descriptionTitle: String, description: String){
    var typeOfDemage: String = typeOfDemage
    var descriptionTitle: String = descriptionTitle
    var description: String = description


    fun Get_description(): String {
        return description
    }


}