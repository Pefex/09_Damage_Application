package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.entities.Damage

@Dao
interface AddressDao {
    // Das ist eine Beschreibung von dem, was ein Schadens-Archivmitarbeiter machen kann.
    @Upsert
    suspend fun upsertAddress(  // einfügen oder ändern eines Schadens-Ordners
        address: Address
    )

    @Delete
    suspend fun deleteAddress(address: Address)  //suspend kennzeichnet, dass hier etwas passiert, das
    //Arbeit in einem bestimmten Scope blockieren kann.

    @Query("SELECT * FROM address ORDER BY postalCode ASC")  //hole (SELECT) alle (*),
// Schäden aus der Schadenstabelle, sortiert nach (ORDER BY), Title (Titelbeschreibung) (descriptionTitle, ASC= aufsteigend)
    fun getAddressesOrderedByTitle(): LiveData<List<Address>>

}