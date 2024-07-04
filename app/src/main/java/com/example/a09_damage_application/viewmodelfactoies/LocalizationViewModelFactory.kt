package com.example.a09_damage_application.viewmodelfactoies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a09_damage_application.repository.LocalizationRepository
import com.example.a09_damage_application.viewmodels.LocalizationViewModel

class LocalizationViewModelFactory (private var localizationRepository: LocalizationRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LocalizationViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LocalizationViewModel(localizationRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}