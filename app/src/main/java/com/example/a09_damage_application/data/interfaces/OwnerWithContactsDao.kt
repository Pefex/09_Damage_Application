package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a09_damage_application.data.entities.OwnerWithContacts

interface OwnerWithContactsDao {
    @Transaction
    @Query("SELECT * FROM Owner")
    fun getOwnersWithContacts(): LiveData<List<OwnerWithContacts>>
}