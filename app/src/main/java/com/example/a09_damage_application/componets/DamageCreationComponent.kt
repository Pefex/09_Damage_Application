package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a09_damage_application.data.Damage
import com.example.a09_damage_application.ui.theme.BoxRounded


class DamageCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun DamageCreationComposable(){

        var selectedTypeOfDamage = remember{
            mutableStateOf("")

        }
        TestTitleComponent().TestTitleComposable("Hallo World")
        DamageTypSelctorComponent().DamageTypSelctorComposable(selectedTypeOfDamage)

        //     Radio Button


        // Titel, Mangeltext und Mangelliste
        var descriptionTitleInput by remember {
            mutableStateOf("")
        }
        //=== to handle onchange in textfield
        var descriptionInput by remember {
            mutableStateOf("")
        }


        //=== to handle onclick to add to list of Lazycolumn
        var damageList = remember {
            mutableStateListOf<Damage>() // Hier kann man auch schon Einträge übergeben.
        }


        Column (modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            //Texteingabe für Titel
            OutlinedTextField(modifier = Modifier
                .border(3.dp, Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))
                .fillMaxWidth(), value = descriptionInput, onValueChange = {descriptionInput= it}, // it ist Platzhalter für das jeweilige Element.
                label = { Text("Titel") },
            )
            //Texteingabe für Mangeltext
            OutlinedTextField( modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .border(3.dp, Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp)),
                value = descriptionTitleInput, onValueChange = {descriptionTitleInput= it},
                label = { Text("Mangelbeschreibung") },
            )
            //padding in button to get top margin from textfield to button
            Button(onClick = { var d: Damage = Damage(selectedTypeOfDamage.value ,descriptionInput, descriptionTitleInput ); ;// Es wird ein neues Objekt der Klasse Damage erzeugt.
                damageList.add(d);descriptionTitleInput = "" ;descriptionInput = ""}, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {
                Text(text = "Add to List", fontSize = 28.sp)
            }

            //LazyColumn(){ items(items=damageList) { Text(text = ""+it.description + it.number, fontSize = 28.sp)}
            //}

            Column (modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .background(Color(37, 150, 190, 150))){
                LazyColumn(modifier = Modifier

                    .padding(1.5.dp)
                    //.border(2.dp, color = Color.Red)
                    .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

                ){ items(items=damageList) {


                    Box(
                        BoxRounded().boxRounded
                    ){
                        Column {
                            Text(text = ""+it.typeOfDemage
                                //+ it.number,
                                ,fontSize = 18.sp)
                            Text(text = ""+it.descriptionTitle
                                //+ it.number,
                                ,fontSize = 18.sp)
                            Text(text = ""+it.description
                                //+ it.number,
                                ,fontSize = 18.sp)
                        }
                    }
                }
                }
            }
        }


    }
}