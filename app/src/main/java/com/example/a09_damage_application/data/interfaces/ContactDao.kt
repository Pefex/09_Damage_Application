package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Contact
import com.example.a09_damage_application.data.entities.ContactWithAddress

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(
        contact: Contact
    )

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY mailaddress ASC")
    fun getContactOrderByTitle(): LiveData<List<Contact>>

    @Transaction
    @Query("SELECT * FROM Contact")
    fun getContactsWithAddress(): LiveData<List<ContactWithAddress>>


}