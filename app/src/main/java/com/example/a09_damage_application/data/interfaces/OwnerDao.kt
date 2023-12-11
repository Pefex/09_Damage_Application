package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Damage

@Dao // Ist der Archiv-Mitarbeiter der zwischen dem Archiv und dem Büro hin und her läuft.
interface OwnerDao {
    @Upsert
    suspend fun upsertDamage(  // einfügen oder ändern eines Schadens-Ordners
        damage: Damage
    )

    @Delete
    suspend fun deleteDamage(damage: Damage)  //suspend kennzeichnet, dass hier etwas passiert, das
    //Arbeit in einem bestimmten Scope blockieren kann.

    @Query("SELECT * FROM damage ORDER BY descriptionTitle ASC")  //hole (SELECT) alle (*),
// Schäden aus der Schadenstabelle, sortiert nach (ORDER BY), Title (Titelbeschreibung) (descriptionTitle, ASC= aufsteigend)
    fun getDamagesOrderedByTitle(): LiveData<List<Damage>>

}