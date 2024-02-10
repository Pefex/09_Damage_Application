package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a09_damage_application.data.entities.ContactWithAddress


interface ContactWithAddressDao {

    @Transaction
    @Query("SELECT * FROM Contact")
    fun getContactsWithAddress(): LiveData<List<ContactWithAddress>>
}