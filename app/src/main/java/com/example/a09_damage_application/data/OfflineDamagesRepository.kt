package com.example.a09_damage_application.data

import com.example.a09_damage_application.interfaces.DamageDao
import com.example.a09_damage_application.interfaces.DamagesRepository
import kotlinx.coroutines.flow.Flow

class OfflineDamagesRepository (private val damageDao: DamageDao) : DamagesRepository {
    override fun getAllItemsStream(): Flow<List<Damage>> = damageDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Damage?> = damageDao.getItem(id)

    override suspend fun insertItem(item: Damage) = damageDao.insert(item)

    override suspend fun deleteItem(item: Damage) = damageDao.delete(item)

    override suspend fun updateItem(item: Damage) = damageDao.update(item)
}