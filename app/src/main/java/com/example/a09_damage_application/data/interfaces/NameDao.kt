package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Name
import java.util.jar.Attributes

@Dao
interface NameDao {
    @Upsert
    suspend fun upsertName(
       name: Name
    )

    @Delete
    suspend fun deleteName(
       name: Name
    )

    @Query("SELECT * FROM name ORDER BY lastName ASC")

    fun getNamesOrderedByTitle(): LiveData<List<Name>>


}