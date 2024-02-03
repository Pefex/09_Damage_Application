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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a09_damage_application.data.enums.TypeOfGender
import com.example.a09_damage_application.ui.theme.AppBackground

class GenderTypeSelectorComponent {
    @Composable
    fun GenderTypeSelectorComposable(selectedTypeOfGender: MutableState<TypeOfGender>){
        Column (modifier = Modifier
            .height(30.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.size(12.dp))
            Row (modifier = Modifier
                .height(40.dp)
                //.background(Color(37, 150, 190, 150))
                .background(AppBackground),
                horizontalArrangement =  Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ){
                Box  (modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()){

                    Row {
                        Row(modifier = Modifier
                            .weight(0.35f)
                            .fillMaxWidth(),
                            horizontalArrangement =  Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(selected = selectedTypeOfGender.value == TypeOfGender.FEMALE,
                                onClick = { selectedTypeOfGender.value = TypeOfGender.FEMALE/*TODO*/ });
                            Text(text = "Frau",  fontWeight = FontWeight.Medium, fontSize = 12.sp)

                        }
                        Row(modifier = Modifier
                            .weight(0.35f)
                            .fillMaxWidth(),
                            horizontalArrangement =  Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically){
                            RadioButton(selected = selectedTypeOfGender.value == TypeOfGender.MALE,
                                onClick = { selectedTypeOfGender.value = TypeOfGender.MALE/*TODO*/ })
                            Text(text = "Herr",  fontWeight = FontWeight.Medium, fontSize = 12.sp)

                        }

                        Row(modifier = Modifier
                            .weight(0.4f)
                            .fillMaxWidth(),
                            horizontalArrangement =  Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically){
                            RadioButton(selected = selectedTypeOfGender.value == TypeOfGender.DIVERS,
                                onClick = { selectedTypeOfGender.value = TypeOfGender.DIVERS/*TODO*/ })
                            Text(text = "Divers",  fontWeight = FontWeight.Medium, fontSize = 12.sp)

                        }
                        /*
                        RadioButton(selected = selectedTypeOfGender.value == TypeOfGender.MALE,
                            onClick = { selectedTypeOfGender.value = TypeOfGender.MALE/*TODO*/ })
                        Spacer(modifier = Modifier.size(10.dp))

                        RadioButton(selected = selectedTypeOfGender.value == TypeOfGender.FEMALE,
                            onClick = { selectedTypeOfGender.value = TypeOfGender.FEMALE/*TODO*/ })
                        Spacer(modifier = Modifier.size(10.dp))

                        RadioButton(selected = selectedTypeOfGender.value == TypeOfGender.DIVERS,
                            onClick = { selectedTypeOfGender.value = TypeOfGender.DIVERS/*TODO*/ })
                        Spacer(modifier = Modifier.size(10.dp))*/

                    }

                }
            }
        }
    }
}