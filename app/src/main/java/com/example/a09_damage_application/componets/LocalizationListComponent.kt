package com.example.a09_damage_application.componets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.LocalizationEvent
import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.data.interfaces.LocalizationDao
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LocalizationListComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun LocalizationListComposable(
        dao: LocalizationDao,
        rootLocalizationId: Int? = 0,
        onNavigateDamage: () ->Unit,

       // onNavigateLocalization: (id: Int)  ->Unit
        ) {

        val coroutineScope = rememberCoroutineScope()

        var localizationId = remember {
            mutableStateOf(rootLocalizationId)


        }

        var localizationName by remember()

         {
            //Log.d("", "localizationId:" + localizationId)
            mutableStateOf<String?>(null)

        }

        var localizationSubs =  remember {
            mutableStateListOf<Localization>()
        }

        dao.getLocalizationById(localizationId.value).observe(
            LocalLifecycleOwner.current,
            Observer{localization -> if (localization!= null){


                localizationName = localization.name
            }}
            )

        dao.getSubLocalizationsById(localizationId.value).observe( //Wenn sich Werte in der Liste ändern,
            // dann wird die Liste gelöscht und neu befüllt.
            LocalLifecycleOwner.current, //Es wird nur solange der aktuelle Lifecycle
            // (Screen) läuft, beobachtet, ob sich die Einträge in der Schadenstabelle
            // der Datenbank verändert haben.
            Observer { allSubs ->
                localizationSubs.clear()
                localizationSubs.addAll(allSubs)
            })

        fun onEvent(event: LocalizationEvent){
            when(event){
                is LocalizationEvent.SaveLocalization  -> {
                    coroutineScope.launch {dao.upsertLocalization(event.localization)  }
                }

                is LocalizationEvent.DeleteLocalization  -> {
                    coroutineScope.launch {dao.deleteLocalization(event.localization)  }
                }

                else -> {}

            }
        }

        var localizationInput by remember {
            mutableStateOf("")
        }

        val numbers = listOf("Felix", "Peter", "Walter", "Frank", "Jürgen")

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
                Button(onClick = {
                    onNavigateDamage()}) {
                    Text(text = "Zu Schäden", fontSize = 14.sp)

                }
            }

            Row (modifier = Modifier
                .width(300.dp)
                .height(80.dp)


            ){
                Text(text = localizationName ?: "Liegenschaft")
            }

            Box(modifier = Modifier
                .width(270.dp)
                .height(80.dp)
                .background(Color.White)

            ){
                Column (modifier = Modifier
                    .width(270.dp)){
                    Row {
                        Column (
                            modifier = Modifier
                                .height(80.dp)
                                .width(200.dp)
                                .background(Color.Yellow)
                        ){
                            OutlinedTextField(modifier = Modifier
                                .background(color = Color(255, 255, 255))
                                .border(
                                    3.dp,
                                    Color(37, 150, 190, 150),
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .fillMaxWidth(), value = localizationInput,
                                onValueChange = {localizationInput= it}, // it ist der Wert im Texteingabefeld
                                label = { Text("neuer Ort") },
                            )
                        }

                        Column (
                            modifier = Modifier
                                .height(80.dp)
                                .width(250.dp)
                        ){

                            IconButton(onClick = {
                                var l: Localization = Localization(
                                    name = localizationInput,
                                    rootLocalizationId = localizationId.value
                                );
                                onEvent(LocalizationEvent.SaveLocalization(l))
                                localizationInput = ""

                            }) {
                                Icon(imageVector = Icons.Default.Add,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(40.dp),
                                    tint= AppBlue,)
                            }



                        }
                    }
                }
            }

            Spacer(modifier = Modifier
                .height(30.dp))

            Column (modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .height(500.dp)
                .width(300.dp)
                //.height(28.dp)
                .background(AppBlue)){
                LazyColumn(modifier = Modifier

                    .padding(1.5.dp)
                    //.border(2.dp, color = Color.Red)
                    .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

                ){ items(items=localizationSubs) {


                    Box(modifier = Modifier
                        .clickable { localizationId.value = it.localizationId}
                        .background(Color.White),
                        //BoxRounded().boxRounded
                    ){
                        Column {
                            Row {
                                Column (
                                    modifier = Modifier
                                        .height(80.dp)
                                        .width(250.dp)
                                ){
                                    Text(text = ""+it.name
                                        //+ it.number,
                                        ,fontSize = 18.sp,
                                        color = AppBlue
                                    )

                                    IconButton(onClick = {
                                        onEvent(LocalizationEvent.DeleteLocalization(it))

                                    }) {
                                        Icon(imageVector = Icons.Default.Delete,
                                            contentDescription = "Favorite",
                                            modifier = Modifier.size(40.dp),
                                            tint= AppBlue,)
                                    }
                                }  /*

                                Column {
                                    Box(modifier = Modifier
                                        .width(30.dp)
                                        .heightIn(10.dp),
                                        contentAlignment = Alignment.Center)
                                    {
                                        BadgedBox(badge = {

                                        }
                                        )
                                        {
                                            IconButton(onClick = {onNavigateDamageLocalization()}) {
                                                Icon(imageVector = Icons.Default.Add,
                                                    contentDescription = "Favorite",
                                                    modifier = Modifier.size(40.dp),
                                                    tint= AppBlue,)
                                            }
                                        }
                                    }

                                    Box(modifier = Modifier
                                        .width(30.dp)
                                        .heightIn(30.dp),
                                        contentAlignment = Alignment.Center)
                                    {
                                        BadgedBox(badge = {

                                        }
                                        )
                                        {
                                            IconButton(onClick = {  }) {
                                                Icon(imageVector = Icons.Default.Create,
                                                    contentDescription = "Favorite",
                                                    modifier = Modifier.size(40.dp),
                                                    tint= AppBlue,)
                                            }
                                        }
                                    }

                                    Box(modifier = Modifier
                                        .width(30.dp)
                                        .heightIn(30.dp),
                                        contentAlignment = Alignment.Center)
                                    {
                                        BadgedBox(badge = {

                                        }
                                        )
                                        {
                                            IconButton(onClick = {  }) {
                                                Icon(imageVector = Icons.Default.Delete,
                                                    contentDescription = "Favorite",
                                                    modifier = Modifier.size(40.dp),
                                                    tint= AppBlue,)
                                            }
                                        }
                                    }

                                }*/

                            }

                            /*
                                                        Icon(
                                                            modifier = Modifier.size(20.dp),
                                                            tint= Color.Cyan,
                                                            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer), contentDescription = "Icon")
                                                        Icon(
                                                            modifier = Modifier.size(20.dp),
                                                            tint= AppBlue,
                                                            painter = painterResource(id = androidx.core.R.drawable.ic_call_decline_low), contentDescription = "Icon")


                                                        Button(onClick = {



                                                        },
                                                            colors = ButtonDefaults.buttonColors
                                                                (contentColor = Color.White,   //pre-created colour
                                                                containerColor = AppBlue),

                                                            modifier = Modifier
                                                                .size(170.dp, 40.dp))

                                                        {
                                                            Text(text = "Delete", fontSize = 12.sp)
                                                        }

                                                        Box(modifier = Modifier
                                                            .width(70.dp)
                                                            .heightIn(70.dp),
                                                            contentAlignment = Alignment.Center)
                                                        {
                                                            BadgedBox(badge = {
                                                                Badge {
                                                                    Text(text = "99+")
                                                                }
                                                            }
                                                            )
                                                            {
                                                                Icon(imageVector = Icons.Default.Favorite,
                                                                    contentDescription = "Favorite",
                                                                    modifier = Modifier.size(40.dp))
                                                            }
                                                        }*/

                        }
                    }
                }
                }



        }
    }
}}

