package com.example.a09_damage_application.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AddressCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun AddressCreationComposable(){
        var text by rememberSaveable { mutableStateOf("") }



        Column (modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
            .padding(2.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Straße") },
                singleLine = true
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Hausnummer") },
                singleLine = true
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Postleitzahl") },
                singleLine = true
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Ort") },
                singleLine = true
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Land") },
                singleLine = true
            )


        }



    }
}