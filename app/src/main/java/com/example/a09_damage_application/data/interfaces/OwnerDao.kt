package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Owner

@Dao
interface OwnerDao {

    @Upsert
    suspend fun upsertOwner(
        owner: Owner
    )

    @Delete
    suspend fun deleteOwner(owner: Owner)

    @Query("DELETE FROM owner WHERE ownerId = :ownerId")
    suspend fun deleteOwnerById(ownerId: Int)

    @Query("SELECT * FROM owner ORDER BY ownerId ASC")
    fun getOwnerOrderByTitle(): LiveData<List<Owner>>

    @Query("SELECT * FROM owner WHERE ownerId = :id")
    fun getOwnerOrderById(id: Int): LiveData<Owner>


}