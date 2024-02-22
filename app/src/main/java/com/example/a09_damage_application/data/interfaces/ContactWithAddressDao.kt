package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.a09_damage_application.data.combinedData.ContactWithAddress

@Dao
interface ContactWithAddressDao {
    @Query(
        "SELECT " +
                "contactId AS contactId, " +
                "addressId AS addressId, " +
                "street AS street, " +
                "streetNumber AS streetNumber, " +
                "postalCode AS postalCode, " +
                "city AS city, " +
                "country AS country, " +
                "isBusinessContact AS isBusinessContact, " +
                "businessName AS businessName, " +
                "telephoneNumberMobil AS telephoneNumberMobil, " +
                "telephoneNumberLandline AS telephoneNumberLandline, " +
                "mailAddress AS mailAddress " +
        "FROM address, contact " +
        "WHERE addressId = contactAddressId")
    fun getContactsWithAddress(): LiveData<List<ContactWithAddress>>

    @Query(
        "SELECT " +
                "contactId AS contactId, " +
                "addressId AS addressId, " +
                "street AS street, " +
                "streetNumber AS streetNumber, " +
                "postalCode AS postalCode, " +
                "city AS city, " +
                "country AS country, " +
                "isBusinessContact AS isBusinessContact, " +
                "businessName AS businessName, " +
                "telephoneNumberMobil AS telephoneNumberMobil, " +
                "telephoneNumberLandline AS telephoneNumberLandline, " +
                "mailAddress AS mailAddress " +
                "FROM address, contact " +
                "WHERE addressId = contactAddressId AND contactId = :contactWithId")
    fun getContactWithAddress(contactWithId: Int): LiveData<ContactWithAddress>

}