package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.Localization


    @Dao
    interface LocalizationDao {

        @Upsert
        suspend fun upsertLocalization(
            localization: Localization
        )

        @Delete
        suspend fun deleteLocalization(localization: Localization)

        @Query("SELECT * FROM localization ORDER BY name ASC")
        fun getLocalizationsOrderByName(): LiveData<List<Localization>>

        @Query("SELECT * FROM localization WHERE localizationId = :id")
        fun getLocalizationById(id: Int): LiveData<Localization>

        @Query("SELECT * FROM localization WHERE rootLocalizationId = :id ORDER BY name ASC")
        fun getSubLocalizationsById(id: Int): LiveData<Localization>


    }
