package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Contact
import com.example.a09_damage_application.data.entities.Owner

interface AddAndEditOwnerDao {

    @Upsert
    suspend fun upsertContact(
        owner: Owner
    )

    @Delete
    suspend fun deleteContact(owner: Owner)

    @Query("SELECT * FROM owner ORDER BY mailaddress ASC")
    fun getContactOrderByTitle(): LiveData<List<Owner>>


}