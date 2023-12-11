package com.example.a09_damage_application.componets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.a09_damage_application.data.interfaces.OwnerDao

class OwnerCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun DamageCreationComposable(dao: OwnerDao){
        val coroutineScope = rememberCoroutineScope()
}
}