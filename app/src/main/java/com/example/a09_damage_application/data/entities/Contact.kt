package com.example.a09_damage_application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val contactId: Int? = null,
    val userId: Int? = null, //" val addressId: Int" bedeutet, das in der Datenbankl dieser Wert gesetzt werden muss.
    val addressId: Int? = null,
    val telephoneNumberMobil: String,
    val telephoneNumberLandline: String,
    val mailAddress: String

)
