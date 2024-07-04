package com.example.a09_damage_application.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.repository.LocalizationRepository
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocalizationViewModel (private var localizationRepository: LocalizationRepository) : ViewModel(){

    private var _localizations = MutableStateFlow<List<Localization>>(emptyList())
    val localizations: StateFlow<List<Localization>> = _localizations

    init{
        getAllLocalizations()
    }

    private fun getAllLocalizations(){
        viewModelScope.launch{
            _localizations.value = localizationRepository.getAllLocalizations()

        }
    }
}