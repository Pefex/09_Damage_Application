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
import com.example.a09_damage_application.data.enums.TypeOfDamage

class DamageTypSelctorComponent {
    @Composable
    fun DamageTypSelctorComposable(selectedTypeOfDamage: MutableState<TypeOfDamage>){

        var rowColor by remember { mutableStateOf(Color(55, 87, 65,66)) }
        //rowColor = Color(55, 87, 65,66) // rember Aktualisierung des Sreens bei
        // Änderung eines abhängigen Wertes

        when(selectedTypeOfDamage.value){
            TypeOfDamage.OPTIC -> rowColor = Color(55, 87, 65,66)
            TypeOfDamage.DANGER_TO_SUBSTANCE -> rowColor = Color(55, 187, 65,66)
            TypeOfDamage.IMMINENT_DANGER-> rowColor = Color(250, 0, 0,66)
            TypeOfDamage.NOTHING_SELECTED -> Color(55, 87, 65,66)
        }

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
                    Text(text = selectedTypeOfDamage.value.description)  // value brauche ich weil
                    // selectedTypeOfDamage ein "MutableState" ist.

                }

                Box (modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()){
                    Row {
                        RadioButton(selected = selectedTypeOfDamage.value == TypeOfDamage.OPTIC,//Hier
                            // wird nicht der Text des Schadens, sondern nur der Schadestyp verglichen
                            onClick = {selectedTypeOfDamage.value = TypeOfDamage.OPTIC
                                },
                            colors = RadioButtonDefaults.colors(Color.Gray))
                        Spacer(modifier = Modifier.size(10.dp))

                        RadioButton(selected = selectedTypeOfDamage.value == TypeOfDamage.DANGER_TO_SUBSTANCE,
                            onClick = {selectedTypeOfDamage.value = TypeOfDamage.DANGER_TO_SUBSTANCE},
                            colors = RadioButtonDefaults.colors(Color.Yellow))
                        Spacer(modifier = Modifier.size(10.dp))

                        RadioButton(selected = selectedTypeOfDamage.value == TypeOfDamage.IMMINENT_DANGER,
                            onClick = {selectedTypeOfDamage.value = TypeOfDamage.IMMINENT_DANGER},
                            colors = RadioButtonDefaults.colors(Color.Red))
                        Spacer(modifier = Modifier.size(10.dp))

                        //selectedTypeOfDamage.value = MainActivity.Type_Of_Demage.visualDefect
                    }
                }
            }
        }

    }
}