package com.example.a09_damage_application.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.entities.Damage
import com.example.a09_damage_application.data.interfaces.AddressDao
import com.example.a09_damage_application.data.interfaces.DamageDao
@Database(
    entities = [Damage :: class, Address :: class],
    version = 1)
abstract class AppDatabase : RoomDatabase (){ // Die Beschreibung des Schadens-Archives als AppDatabase.
    abstract val damageDao: DamageDao
    abstract val addressDao: AddressDao
}

