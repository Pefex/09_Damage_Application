package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.example.a09_damage_application.MainActivity
import com.example.a09_damage_application.data.TYPE_OF_DAMAGE

class DamageTypSelctorComponent {
    @Composable
    fun DamageTypSelctorComposable(selectedTypeOfDamage: MutableState<String>){

        var rowColor by remember { mutableStateOf(Color(55, 87, 65,66)) }
        //rowColor = Color(55, 87, 65,66)

        when(selectedTypeOfDamage.value){
            "Optischer Mangel" -> rowColor = Color(55, 87, 65,66)
            "Gefahr für Bausubstanz" -> rowColor = Color(55, 187, 65,66)
            "Gefahr in Verzug" -> rowColor = Color(250, 0, 0,66)
        }
/*
        if (selectedTypeOfDamage.value == "Optischer Mangel"){
            rowColor = Color(55, 87, 65,66)
        }
        if (selectedTypeOfDamage.value == "Gefahr für Bausubstanz"){
            rowColor = Color(55, 187, 65,66)
        }
        else{
            if (selectedTypeOfDamage.value == "Gefahr in Verzug"){
                rowColor = Color(250, 0, 0,66)
            }
        }*/

        Column (modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            Text(text = "Ausgewählter Schaden",)
            Spacer(modifier = Modifier.size(12.dp))

            Row (modifier = Modifier
                .height(40.dp)
                //.background(Color(37, 150, 190, 150))
                .background(rowColor),
                horizontalArrangement =  Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ){

                Box (modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                    contentAlignment = Alignment.Center)
                {
                    Text(text = selectedTypeOfDamage.value)

                }

                Box (modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()){
                    Row {
                        RadioButton(selected = selectedTypeOfDamage.value == TYPE_OF_DAMAGE.Type_Of_Demage.visualDefect,
                            onClick = {selectedTypeOfDamage.value = TYPE_OF_DAMAGE.Type_Of_Demage.visualDefect
                                },
                            colors = RadioButtonDefaults.colors(Color.Gray))
                        Spacer(modifier = Modifier.size(10.dp))

                        RadioButton(selected = selectedTypeOfDamage.value == TYPE_OF_DAMAGE.Type_Of_Demage.dangerToTheBuildingFabric,
                            onClick = {selectedTypeOfDamage.value = TYPE_OF_DAMAGE.Type_Of_Demage.dangerToTheBuildingFabric},
                            colors = RadioButtonDefaults.colors(Color.Yellow))
                        Spacer(modifier = Modifier.size(10.dp))

                        RadioButton(selected = selectedTypeOfDamage.value == TYPE_OF_DAMAGE.Type_Of_Demage.dangerInDelay,
                            onClick = {selectedTypeOfDamage.value = TYPE_OF_DAMAGE.Type_Of_Demage.dangerInDelay},
                            colors = RadioButtonDefaults.colors(Color.Red))
                        Spacer(modifier = Modifier.size(10.dp))

                        //selectedTypeOfDamage.value = MainActivity.Type_Of_Demage.visualDefect
                    }
                }
            }
        }

    }
}