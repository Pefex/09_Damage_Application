package com.example.a09_damage_application.data.combinedData

data class ContactWithAddress (
    //Contact id (to enable edit and delete)
    val contactId: Int,
    val addressId: Int?,

    //Address values
    val street: String,
    val streetNumber: String,
    val postalCode: String,
    val city: String,
    val country: String,

    //Contact values
    val isBusinessContact: Boolean,
    val businessName: String?,
    val userId: Int? = null, //" val addressId: Int" bedeutet, das in der Datenbankl dieser Wert gesetzt werden muss.
    val telephoneNumberMobil: String,
    val telephoneNumberLandline: String,
    val mailAddress: String
)