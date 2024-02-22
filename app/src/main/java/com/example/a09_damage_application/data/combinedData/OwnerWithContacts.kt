package com.example.a09_damage_application.data.combinedData

data class OwnerWithContacts(
    val ownerId: Int,
    //TODO include name
    val privateContact: ContactWithAddress?,
    val businessContact: ContactWithAddress?,
)
