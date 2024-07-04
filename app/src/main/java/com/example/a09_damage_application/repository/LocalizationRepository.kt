package com.example.a09_damage_application.repository

import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.data.interfaces.LocalizationDao

class LocalizationRepository (private var localizationDao: LocalizationDao){
    suspend fun getAllLocalizations(): List<Localization>{
        return localizationDao.getAllLocalizations()
    }
}