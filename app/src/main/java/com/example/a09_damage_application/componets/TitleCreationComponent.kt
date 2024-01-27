package com.example.a09_damage_application.componets

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class TitleCreationComponent {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable

    fun TitleCreationComposable(){
        var isExpanded by remember{
            mutableStateOf(false)
        }

        var nameTitle by remember{

            mutableStateOf("")
        }

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {

                TextField(value = nameTitle, onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor())

                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {isExpanded = false }) {

                    DropdownMenuItem(
                        text = { Text(text = "Ohne Titel") },
                        onClick = { nameTitle = "Ohne Titel"
                            isExpanded = false })

                    DropdownMenuItem(
                        text = { Text(text = "Dipl.-Ing.") },
                        onClick = { nameTitle = "Dipl.-Ing."
                            isExpanded = false })

                    DropdownMenuItem(
                        text = { Text(text = "Dr.") },
                        onClick = { nameTitle = "Dr."
                            isExpanded = false })

                }
            }

        }

        Log.d(ContentValues.TAG,"Hallo")
        Log.d(ContentValues.TAG,nameTitle)
    }


}