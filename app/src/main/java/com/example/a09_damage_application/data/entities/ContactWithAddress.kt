package com.example.a09_damage_application.data.entities

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class ContactWithAddress(
    @Embedded val contact: Contact,
    @Relation(
        parentColumn = "contactId",
        entityColumn = "addressContactId",
    )
    val address: Address
)
