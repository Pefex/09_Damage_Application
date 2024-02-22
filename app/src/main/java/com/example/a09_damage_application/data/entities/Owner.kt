
package com.example.a09_damage_application.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Owner(
    @PrimaryKey(autoGenerate = true)
    val ownerId: Int? = null,  //"? = null" bedeutet, das in der Datenbankl dieser Wert nicht gesetzt werden muss.
    val nameId: Int,
    val privateContactId: Int? = null,
    val businessContactId: Int? = null,
)
