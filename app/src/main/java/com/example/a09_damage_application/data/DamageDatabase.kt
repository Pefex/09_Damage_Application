package com.example.a09_damage_application.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Damage::class],
    version = 1
)

abstract class DamageDatabase: RoomDatabase() {
    abstract val dao: DamageDao
}