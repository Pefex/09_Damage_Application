package com.example.a09_damage_application.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class OwnerWithContacts(
    @Embedded val owner: Owner,
    @Relation(
        parentColumn = "ownerId",
        entityColumn = "userId",
    )
    val privateContact: ContactWithAddress,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "userId",
    )
    val businessContact: ContactWithAddress)
