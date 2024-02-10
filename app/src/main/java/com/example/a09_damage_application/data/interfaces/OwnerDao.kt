package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Owner
import com.example.a09_damage_application.data.entities.OwnerWithContacts

@Dao
interface OwnerDao {

    @Upsert
    suspend fun upsertOwner(
        owner: Owner
    )

    @Delete
    suspend fun deleteOwner(owner: Owner)

    @Query("SELECT * FROM owner ORDER BY ownerId ASC")
    fun getOwnerOrderByTitle(): LiveData<List<Owner>>

    @Transaction
    @Query("SELECT * FROM Owner")
    fun getOwnersWithContacts(): LiveData<List<OwnerWithContacts>>


}