package com.example.a09_damage_application.componets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.a09_damage_application.data.interfaces.AddAndEditOwnerDao
import com.example.a09_damage_application.data.interfaces.NameDao

class AddAndEditOwnersComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun AddAndEditOwnersComposable(dao: AddAndEditOwnerDao, onNavigateDamage: ()->Unit, onNavigateAddress: ()->Unit){

    }
}