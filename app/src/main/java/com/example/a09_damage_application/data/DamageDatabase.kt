package com.example.a09_damage_application.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a09_damage_application.data.entities.Damage
import com.example.a09_damage_application.data.interfaces.DamageDao
@Database(
    entities = [Damage :: class],
    version = 1)
abstract class DamageDatabase : RoomDatabase (){ // Die Beschreibung des Schadens-Archives als DamageDatabase.
    abstract val damageDao: DamageDao
}