package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.entities.Name
import com.example.a09_damage_application.data.entities.OwnerList

@Dao
interface OwnerListDao {

    @Upsert
    suspend fun upsertOwnerList(  // einfügen oder ändern eines Schadens-Ordners
        ownerList: OwnerList
    )

    @Delete
    suspend fun deleteName(
        ownerList: OwnerList
    )



}