package com.example.a09_damage_application.data.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.a09_damage_application.data.entities.BuildingElement
import com.example.a09_damage_application.data.entities.Localization


@Dao
interface BuildingElementDao {

    @Upsert
    suspend fun upsertBuildingElement(
        buildingElement: BuildingElement
    )

    @Delete
    suspend fun deleteBuildingElement (buildingElement: BuildingElement)

   @Query("SELECT * FROM buildingElement ORDER BY name ASC")// by positionID
   fun getBuildingElementsOrderByName(): LiveData<List<BuildingElement>>

    @Query("SELECT * FROM buildingElement WHERE buildingElementId = :id")
    fun getBuildingElementsById(id: Int?): LiveData<BuildingElement> //"LiveData" bewirkt, dass wir immer die aktuellen Werte (die aktuelle Entity) aus der Datenbank holen.
    // Immer wenn LIveData verwendet wird, dann sollte/muß ein observer (in der Component) eingesetzt werden - er beobachtet Veränderungen "Localization" ist die entity

    @Query("SELECT * FROM buildingElement WHERE buildingElementId = :id")
    fun getBuildingElementByIdOnce(id: Int?): BuildingElement //wird nur einmal auf Aufruf ausgeführt und nicht ständig aktualisiert

    @Query("SELECT * FROM buildingElement WHERE rootLocalizationId = :id ORDER BY name ASC")
    fun getSubBuildingElementsById(id: Int?): LiveData<List<BuildingElement>>

    @Query("SELECT * FROM buildingElement WHERE rootLocalizationId = :id ORDER BY name ASC")
    fun getSubBuildingElementsByIdOnce(id: Int?): List<BuildingElement>


}