package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.example.a09_damage_application.data.enums.ItemCategory

import com.example.a09_damage_application.data.interfaces.LocalizationDao
//
class AddAndEditItemComponent {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable

    fun DamageLocalizationAddAndEditCreationComposable(


        dao: LocalizationDao,


        onNavigateDamageLocalization: () ->Unit
    ) {

        var selectedTypeOfDamageLocalizationItem by remember{
            mutableStateOf(false)
        }

        var SelectLocalizationDamageDropdownMenuIsExpanded by remember{
            mutableStateOf(false)
        }

        var selectLocalizationDamage by remember{

            mutableStateOf("")
        }


        Column(
            modifier = Modifier
                .height(600.dp)
                .width(400.dp)
                .padding(5.dp)
                .background(Color.Green)
            //.verticalScroll(rememberScrollState()).fillMaxSize(),
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row (modifier = Modifier
                .width(300.dp)
                .height(50.dp)


            ){


                Button(onClick = {onNavigateDamageLocalization()}) {
                    Text(text = "Speichern", fontSize = 14.sp)
                }

            }

            Box(modifier = Modifier
                .height(65.dp



                ),
                contentAlignment = Alignment.BottomCenter){
                ExposedDropdownMenuBox(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Magenta), expanded = SelectLocalizationDamageDropdownMenuIsExpanded, onExpandedChange = {SelectLocalizationDamageDropdownMenuIsExpanded = it}) {

                    OutlinedTextField( value = selectLocalizationDamage, onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = SelectLocalizationDamageDropdownMenuIsExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(Color.Black),
                        label = { Text("Titel") },

                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .background(Color.White)
                    )

                    ExposedDropdownMenu(
                        expanded = SelectLocalizationDamageDropdownMenuIsExpanded,
                        onDismissRequest = {SelectLocalizationDamageDropdownMenuIsExpanded = false }) {
                        /*   wird mit for-Schleife umgesetzt
                        DropdownMenuItem(modifier = Modifier
                            .background(Color.White),
                            text = { Text(text = "nichts ausgewählt") },

                            onClick = {
                                selectLocalizationDamage = "nichts ausgewählt";
                                selectedTypeOfDamageLocalizationItem.value =
                                    Type_Of_Damage_Localization_Item.GroundFloor
                                SelectLocalizationDamageDropdownMenuIsExpanded = false
                            })

                        Divider()

                        */


                    }
                }

            }
}}}