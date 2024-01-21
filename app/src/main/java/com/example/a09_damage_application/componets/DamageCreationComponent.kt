package com.example.a09_damage_application.componets

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.Nullable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.DamageEvent
import com.example.a09_damage_application.data.entities.Damage
import com.example.a09_damage_application.data.enums.TypeOfDamage
import com.example.a09_damage_application.data.interfaces.DamageDao
import com.example.a09_damage_application.ui.theme.BoxRounded
import kotlinx.coroutines.launch


class DamageCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun DamageCreationComposable(dao: DamageDao){

        val coroutineScope = rememberCoroutineScope()

        var typeOfDamageInput = remember{
            mutableStateOf(TypeOfDamage.NOTHING_SELECTED) // selectedTypeOfDamage wird mit einem
        // leeren String initalisiert

        }
        TestTitleComponent().TestTitleComposable("Hallo World")
        DamageTypSelctorComponent().DamageTypSelctorComposable(typeOfDamageInput)

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

        dao.getDamagesOrderedByTitle().observe( //Wenn sich Werte in der Liste ändern, dann wird
            // die Liste gelöscht und neu befüllt.
            LocalLifecycleOwner.current, //Es wird nur solange der aktuelle Lifecycle (Screen) läuft, beobachtet,
            //ob sich die Einträge in der Schadenstabelle der Datenbank verändert haben.
            Observer { allDamages ->
                damageList.clear()
                damageList.addAll(allDamages)
            })

       // Log.d(TAG, "Schadensliste: " + damageList.toString())




        fun onEvent(event: DamageEvent){
            when(event){
                is DamageEvent.SaveDamage -> {
                    coroutineScope.launch{ dao.upsertDamage(event.damage) }
                }
                is DamageEvent.DeleteDamage -> {
                    coroutineScope.launch{ dao.deleteDamage(event.damage) }
                }
                else -> {}
            }
        }


        Column (modifier = Modifier
            .fillMaxSize()
            .heightIn(100.dp)
            .padding(2.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            //Texteingabe für Titel
            OutlinedTextField(modifier = Modifier
                .background(color = Color(255, 255, 255))
                .border(3.dp, Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))
                .fillMaxWidth(), value = descriptionInput,
                onValueChange = {descriptionInput= it}, // it ist Platzhalter für das jeweilige Element.
                label = { Text("Titel") },
            )
            //Texteingabe für Mangeltext
            OutlinedTextField( modifier = Modifier
                .padding(top = 10.dp)
                //.fillMaxHeight(0.5f)
                .height(60.dp)
                .fillMaxWidth()
                .border(3.dp, Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp)),
                value = descriptionTitleInput,
                onValueChange = {descriptionTitleInput= it},
                label = { Text("Mangelbeschreibung") },
            )
            //padding in button to get top margin from textfield to button
            Button(onClick = {
                var d: Damage = Damage(
                    typeOfDamage = typeOfDamageInput.value ,
                    description = descriptionInput,
                    descriptionTitle = descriptionTitleInput
                ); ;// Es wird ein neues Objekt der Klasse Damage erzeugt.
                onEvent(DamageEvent.SaveDamage(d))
                //Log.d(TAG, "Addressliste: " + addressList.toString())
                descriptionTitleInput = "" ;descriptionInput = ""}, modifier = Modifier  // Die
                    // Texteingabefelder werden wieder auf null gesetzt.
                .fillMaxWidth()
                .padding(2.dp)) {
                Text(text = "Add to List", fontSize = 28.sp)
            }



            //LazyColumn(){ items(items=damageList) { Text(text = ""+it.description + it.number, fontSize = 28.sp)}
            //}

            Column (modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                //.height(80.dp)
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
                            Text(text = ""+it.typeOfDamage.description
                                //+ it.number,
                                ,fontSize = 18.sp)
                            Text(text = ""+it.descriptionTitle
                                //+ it.number,
                                ,fontSize = 18.sp)
                            Text(text = ""+it.description
                                //+ it.number,
                                ,fontSize = 18.sp)
                            Button(onClick = {
                                var d: Damage = Damage(
                                    typeOfDamage = typeOfDamageInput.value ,
                                    description = descriptionInput,
                                    descriptionTitle = descriptionTitleInput
                                ); ;// Es wird ein neues Objekt der Klasse Damage erzeugt.
                                onEvent(DamageEvent.SaveDamage(d))
                                //Log.d(TAG, "Addressliste: " + addressList.toString())
                                descriptionTitleInput = "" ;descriptionInput = ""}, modifier = Modifier  // Die
                                // Texteingabefelder werden wieder auf null gesetzt.
                                .fillMaxWidth()
                                .padding(2.dp)) {
                                Text(text = "Add to List", fontSize = 28.sp)
                            }
                        }
                    }
                }
                }
            }
            var text by rememberSaveable { mutableStateOf("") }
        }
    }
}