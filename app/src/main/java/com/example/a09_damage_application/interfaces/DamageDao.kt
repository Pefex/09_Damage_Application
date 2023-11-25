package com.example.a09_damage_application.interfaces

import android.content.ClipData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.a09_damage_application.data.Damage
import java.util.concurrent.Flow
@Dao
interface DamageDao {  //Kommunikation von Dao zur Datenbank

    @Query("SELECT * from damages ORDER BY descriptionTitle ASC")
    //fun getAllItems(): Flow<Damage>
    fun getAllItems(): kotlinx.coroutines.flow.Flow<List<Damage>>

    @Query("SELECT * from damages WHERE id = :id")
    fun getItem(id: Int): kotlinx.coroutines.flow.Flow<Damage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Damage)

    @Update
    suspend fun update(item: Damage)

    @Delete
    suspend fun delete(item: Damage)


}