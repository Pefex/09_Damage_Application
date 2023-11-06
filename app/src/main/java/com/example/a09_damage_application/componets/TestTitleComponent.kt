package com.example.a09_damage_application.componets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class TestTitleComponent {
    @Composable
    fun TestTitleComposable(title: String){
        Text(text = title)
    }
}