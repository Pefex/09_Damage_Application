package com.example.a09_damage_application.interfaces

import com.example.a09_damage_application.data.Damage
import kotlinx.coroutines.flow.Flow

interface DamagesRepository {
    fun getAllItemsStream(): Flow<List<Damage>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<Damage?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Damage)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: Damage)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Damage)
}