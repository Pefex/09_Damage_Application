package com.example.a09_damage_application.data.interfaces

import android.content.ContentValues.TAG
import android.util.Log
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
        fun getLocalizationsOrderByName(): LiveData<List<Localization>> // Es wird eine komplette
        //entity mit allen Einträgen in einer Liste zurückgegeben, aber nach Namen geordnet

        @Query("SELECT * FROM localization WHERE localizationId = :id")
        fun getLocalizationById(id: Int?): LiveData<Localization> //"LiveData" bewirkt, dass wir immer die aktuellen Werte (die aktuelle Entity) aus der Datenbank holen.
           // Immer wenn LIveData verwendet wird, dann sollte/muß ein observer (in der Component) eingesetzt werden - er beobachtet Veränderungen in der Antwort der Query, "Localization" ist die entity
        // "SELECT * " bedeutet, dass alle Spalten der Tabelle geholt werden.
        // " FROM localization" bedeutet von welcher Tabelle
        // "WHERE localizationId = :id" gib mir nur die Localization zurück, die mit der übergebenen id mit der localizationId übereinstimmt.
        // das " = " ist ein Vergleichsoperator und bewirkt, dass nur dass zurückgegeben wird, wo die Bedingung zutrifft
        // "id" ist der Wert, der der function beim Funktionsaufruf übergeben wird.

        @Query("SELECT * FROM localization WHERE localizationId = :id")
        fun getLocalizationByIdOnce(id: Int?): Localization //wird nur einmal auf Aufruf ausgeführt und nicht ständig aktualisiert, ansonsten wie obere Query

        @Query("SELECT * FROM localization WHERE rootLocalizationId = :id ORDER BY position ASC")
        fun getSubLocalizationsById(id: Int?): LiveData<List<Localization>>  // LiveData






    }
