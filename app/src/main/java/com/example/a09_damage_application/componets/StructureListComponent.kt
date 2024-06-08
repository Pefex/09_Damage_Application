package com.example.a09_damage_application.componets

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.BuildingElementEvent
import com.example.a09_damage_application.data.entities.BuildingElement
import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.data.interfaces.BuildingElementDao
import com.example.a09_damage_application.data.interfaces.LocalizationDao
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded

class StructureListComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun LocalizationBoxComposable( daoLocalization: LocalizationDao,
                                   daoBuildingElement: BuildingElementDao,
                                   localization: Localization,


                                  ){

        var localizationSubs =  remember {  // mit .value
            mutableStateListOf<Localization>()
        }

        var buildingElements =  remember {  // mit .value
            mutableStateListOf<BuildingElement>()
        }



        daoLocalization.getSubLocalizationsById(localization.localizationId).observe( //Wenn sich Werte in der Liste ändern,
            // dann wird die Liste gelöscht und neu befüllt.
            LocalLifecycleOwner.current, //Es wird nur solange der aktuelle Lifecycle
            // (Screen) läuft, beobachtet, ob sich die Einträge in der Schadenstabelle
            // der Datenbank verändert haben.
            Observer { allSubs ->
                localizationSubs.clear()
                localizationSubs.addAll(allSubs)
            })

        daoBuildingElement.getSubBuildingElementsById(localization.localizationId).observe( //Wenn sich Werte in der Liste ändern,
            // dann wird die Liste gelöscht und neu befüllt.
            LocalLifecycleOwner.current, //Es wird nur solange der aktuelle Lifecycle
            // (Screen) läuft, beobachtet, ob sich die Einträge in der Schadenstabelle
            // der Datenbank verändert haben.
            Observer { allSubs ->
                buildingElements.clear()
                buildingElements.addAll(allSubs)
            })

///////////////////////////////////////////////////////

        Box(
            modifier = Modifier

                .background(Color.White),
            //BoxRounded().boxRounded
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp, 5.dp, 10.dp, 5.dp)
                            // .fillMaxHeight()
                            .fillMaxSize()

                            //.height(45.dp)
                            .width(220.dp),

                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "" + localization.name
                            //+ it.number,
                            , fontSize = 18.sp,
                            color = Color.Red
                        )
                        Divider(color = AppBlue, thickness = 1.5.dp)
                    }
                }
                ///////////////////////// Liste der untergeordneten Localizations
                Column(modifier = Modifier
                    //.height((30 + 80 * localizationSubs.size).dp)
                    .offset(x = 5.dp)
                    .clip(shape = RoundedCornerShape(0.5.dp))
                    .padding(1.5.dp)
                    .clip(RoundedCornerShape(1.5.dp))
                    //.padding(1.5.dp)
                    //.border(2.dp, color = Color.Red)
                    .background(Color.Red, shape = RoundedCornerShape(5.dp)),
                    //userScrollEnabled = false
                )
                {
                   /* items(items=localizationSubs){
                        LocalizationBoxComposable(daoLocalization, it,
                        )
                    }*/

                    localizationSubs.forEach { localization->
                        LocalizationBoxComposable(daoLocalization, daoBuildingElement, localization)
                    }
                }

                //////////////////////////////// Liste der angehängten Bauelemente
                ///////// Momentan kann ich nur eine Ebene von Bauelementen anzeigen.

                Column(modifier = Modifier
                    //.height((30 + 80 * localizationSubs.size).dp)
                    .padding(10.dp, 5.dp, 10.dp, 5.dp)
                    .offset(x = 5.dp)
                    .clip(shape = RoundedCornerShape(0.5.dp))
                    .padding(1.5.dp)
                    .clip(RoundedCornerShape(1.5.dp))
                    .padding(1.5.dp)
                    //.border(2.dp, color = Color.Red)
                    .background(Color.Green, shape = RoundedCornerShape(5.dp)),
                    //userScrollEnabled = false
                )
                {

                    buildingElements.forEach { buildingElement->
                        Row {

                            Text(text = buildingElement.name,
                                 fontSize = 18.sp,
                                color = Color.Magenta)
                        }
                    }
                }
            }
        }
    }

    @ExperimentalMaterial3Api
    @Composable

    fun StructureListComposable(
        daoLocalization: LocalizationDao,  // "dao ist die lokale Variable für das "LocalizationDao"
        daoBuildingElement: BuildingElementDao,
        localizationId: Int,
    ) {
        var localizationSubs =  remember {  // mit .value
            mutableStateListOf<Localization>()
        }

        var buildingElements =  remember {  // mit .value
            mutableStateListOf<BuildingElement>()
        }

        daoLocalization.getSubLocalizationsById(0).observe( //Wenn sich Werte in der Liste ändern,
            // dann wird die Liste gelöscht und neu befüllt.
            LocalLifecycleOwner.current, //Es wird nur solange der aktuelle Lifecycle
            // (Screen) läuft, beobachtet, ob sich die Einträge in der Schadenstabelle
            // der Datenbank verändert haben.
            Observer { allSubs ->
                localizationSubs.clear()
                localizationSubs.addAll(allSubs)


            })

        daoBuildingElement.getSubBuildingElementsById(localizationId).observe( //Wenn sich Werte in der Liste ändern,
            // dann wird die Liste gelöscht und neu befüllt.
            LocalLifecycleOwner.current, //Es wird nur solange der aktuelle Lifecycle
            // (Screen) läuft, beobachtet, ob sich die Einträge in der Schadenstabelle
            // der Datenbank verändert haben.
            Observer { allSubs ->
                buildingElements.clear()
                buildingElements.addAll(allSubs)
            })
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                // .fillMaxHeight()
                .height(320.dp)
                .width(330.dp)
                //.height(28.dp)
                .background(AppBlue)
                .verticalScroll(rememberScrollState())
        ) {



            LazyColumn(
                modifier = Modifier
                    .height(310.dp)
                    //.fillMaxHeight()
                    .weight(1f)

                    //.fillMaxHeight()
                    .clip(shape = RoundedCornerShape(0.5.dp))
                    .padding(1.5.dp)
                    .clip(RoundedCornerShape(1.5.dp))

                    .padding(1.5.dp)
                    //.border(2.dp, color = Color.Red)
                    .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

            ) {
                items(items = localizationSubs)
                {
                    LocalizationBoxComposable(daoLocalization,
                        daoBuildingElement, it,  //Hier werden die Boxen der obersten Ebenen erstellt. Hierfür

                        )
                }
            }

        }

        Spacer(modifier = Modifier
            .height(20.dp)
            .background(Color.Black))

        Column (modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .height(220.dp)
            .width(330.dp)
            //.height(28.dp)
            .background(Color.Yellow)){
            LazyColumn(modifier = Modifier
                .clip(shape = RoundedCornerShape(0.5.dp))
                .padding(1.5.dp)
                .clip(RoundedCornerShape(1.5.dp))

                .padding(1.5.dp)
                //.border(2.dp, color = Color.Red)
                .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

            ){ items(items=buildingElements)
            {

                Log.d(TAG, "B  A  U  E  L  E  M  E  N  T  E:  " + buildingElements.toString())
                //Log.d(TAG, "BAUELEMENTE:  " + buildingElementSubs.forEach {it.name  })
                //Log.d(TAG, "BAUELEMENTE:  " + items)

                val myList =  buildingElements

                // Log.d("", "INHALT LISTE:  "  +    myList[1].toString() )
                //Log.d("", "Name Listenelement 1:  "  +    myList[1].name )


                Box(modifier = Modifier
                    //.clickable {  localizationId.value = it.l }
                    .background(Color.White),
                    //BoxRounded().boxRounded
                ){
                    Column {
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Column (
                                modifier = Modifier
                                    .padding(10.dp, 5.dp, 10.dp, 5.dp)
                                    .height(50.dp)
                                    .width(220.dp),

                                verticalArrangement = Arrangement.Center
                            ){
                                Text(text = ""+it.name + " " + it.inspectionType

                                    //+ it.number,
                                    ,fontSize = 18.sp,
                                    color = Color.Black
                                )
                            }

                            //Log.d("","INHALT DER LISTE" + it.name)
                            Column  (
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(45.dp)
                            ){
                                IconButton(onClick = {
                                    //onEvent(BuildingElementEvent.DeleteBuildingElement(it))

                                }) {
                                    Icon(imageVector = Icons.Default.Delete,
                                        contentDescription = "Favorite",
                                        modifier = Modifier.size(30.dp),
                                        tint= AppBlue,)
                                }
                            }

                            Column  (
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(45.dp)
                            ){
                                IconButton(onClick = {
                                    //onEvent(BuildingElementEvent.DeleteBuildingElement(it))

                                }) {
                                    Icon(imageVector = Icons.Default.Create,
                                        contentDescription = "Favorite",
                                        modifier = Modifier.size(30.dp),
                                        tint= AppBlue,)
                                }
                            }
                        }
                    }
                }

                Divider(color = AppBlue, thickness = 1.5.dp)
            }
            }
        }
    }
}

