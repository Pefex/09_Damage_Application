package com.example.a09_damage_application.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.entities.Contact
import com.example.a09_damage_application.data.entities.Damage
import com.example.a09_damage_application.data.entities.Name
import com.example.a09_damage_application.data.entities.Owner
import com.example.a09_damage_application.data.interfaces.AddAndEditOwnerDao
import com.example.a09_damage_application.data.interfaces.AddressDao
import com.example.a09_damage_application.data.interfaces.ContactDao
import com.example.a09_damage_application.data.interfaces.DamageDao
import com.example.a09_damage_application.data.interfaces.NameDao

@Database(
    entities = [Damage :: class, Address :: class, Name :: class, Contact :: class, Owner :: class],
    version = 1)
abstract class AppDatabase : RoomDatabase (){ // Die Beschreibung des Schadens-Archives als AppDatabase.
    abstract val damageDao: DamageDao
    abstract val addressDao: AddressDao
    abstract val nameDao: NameDao
    abstract val contactDao: ContactDao
    abstract val addAndEditOwnerDao: AddAndEditOwnerDao
}


