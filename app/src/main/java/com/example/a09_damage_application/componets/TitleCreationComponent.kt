package com.example.a09_damage_application.componets

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.a09_damage_application.data.enums.TypeOfTitle
import com.example.a09_damage_application.ui.theme.AppBackground
import kotlinx.coroutines.selects.select

class TitleCreationComponent {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable

    fun TitleCreationComposable(selectedTypeOfTitle: MutableState<TypeOfTitle>) {
        var isExpanded by remember{
            mutableStateOf(false)
        }

        var nameTitle by remember{

            mutableStateOf("")
        }

        Column (modifier = Modifier
            .width(350.dp)){

            Box(modifier = Modifier
                .height(65.dp



                ),
                contentAlignment = Alignment.BottomCenter){
                ExposedDropdownMenuBox(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Magenta), expanded = isExpanded, onExpandedChange = {isExpanded = it}) {

                    OutlinedTextField( value = nameTitle, onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(Color.Black),
                        label = { Text("Titel") },

                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .background(Color.White)
                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = {isExpanded = false }) {

                        DropdownMenuItem(modifier = Modifier
                            .background(Color.White),
                            text = { Text(text = "Ohne Titel") },

                            onClick = { nameTitle = "Ohne Titel";
                                selectedTypeOfTitle.value = TypeOfTitle.NOTHING_SELECTED
                                isExpanded = false })

                        Divider()

                        DropdownMenuItem(modifier = Modifier
                            .background(Color.White),
                            text = { Text(text = "Dipl.-Ing.") },
                            onClick = { nameTitle = "Dipl.-Ing.";
                                selectedTypeOfTitle.value = TypeOfTitle.DIPL_ING
                                isExpanded = false })

                        Divider()

                        DropdownMenuItem(modifier = Modifier
                            .background(Color.White),
                            text = { Text(text = "Dr.") },
                            onClick = { nameTitle = "Dr.";
                                selectedTypeOfTitle.value = TypeOfTitle.DOCTOR
                                isExpanded = false })

                    }
                }

            }


        }



        Log.d(ContentValues.TAG,"Hallo")
        Log.d(ContentValues.TAG,nameTitle)
        //selectedTypeOfTitle.value = nameTitle
    }


}