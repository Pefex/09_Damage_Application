package com.example.a09_damage_application.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DamageDao {
    @Upsert
    suspend fun upsertDamage(damage: Damage)
    @Delete
    suspend fun deleteDamage(damage: Damage)
    @Query("SELECT * FROM damage ORDER BY descriptionTitle ASC")
    fun getDamagesOrderedByTitle(): Flow<List<Damage>>
}