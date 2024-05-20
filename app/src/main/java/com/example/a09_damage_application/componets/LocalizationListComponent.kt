package com.example.a09_damage_application.componets

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.room.Dao
import com.example.a09_damage_application.componets.events.LocalizationEvent
import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.data.interfaces.LocalizationDao
import com.example.a09_damage_application.ui.theme.AppBlue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalizationListComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun LocalizationListComposable(
        //selectedTypeOfLevel: MutableState<Type_Of_Level>,
        dao: LocalizationDao,  // "dao ist die lokale Variable für das "LocalizationDao"
        rootLocalizationId: Int? = 0,
        onNavigateDamage: () ->Unit,
        ) {


        var isExpanded by remember{
            mutableStateOf(false)
        }

        var typeOfInspection by remember{
            mutableStateOf("Select a Type of Inspection")
        }


        val coroutineScope = rememberCoroutineScope()

        var localizationId = remember {
            mutableStateOf(rootLocalizationId)
        }

        var rootId by remember(){
            mutableStateOf<Int?>(null)
        }

        var localizationName by remember(){  //ohne .value
            //Log.d("", "localizationId:" + localizationId)
            mutableStateOf<String?>(null)
        }

        var localizationLevel by remember {
            mutableStateOf<Int>(0)
        }

        var path by remember {
            mutableStateOf<String?>(null)
        }

        var localizationSubs =  remember {  // mit .value
            mutableStateListOf<Localization>()
        }

        /////////////////////////////////////////////////////////
        val items_localization = listOf("", "EG", "1. OG", "2. OG", "3. OG", "KG")
        val selectedItem_localization = "" // Setze hier den ausgewählten Wert
        val label_localization = "Select a Localization" // Setze hier den Label-Text
        //val label_buildingElement = "Select a Localization" // Setze hier den Label-Text

        var expanded_localization by remember { mutableStateOf(false) }
        var listItems_localizazion by remember(items_localization, selectedItem_localization) {
            mutableStateOf(
                if (selectedItem_localization.isNotEmpty()) {
                    items_localization.filter { x -> x.startsWith(selectedItem_localization.lowercase(), ignoreCase = true) }
                } else {
                    items_localization.toList()
                }
            )
        }

        var listItemsNew_localization by remember(items_localization, selectedItem_localization) {
            mutableStateOf(
                if (selectedItem_localization.isNotEmpty()) {
                    items_localization.filter { x -> x.startsWith(selectedItem_localization.lowercase(), ignoreCase = true) }
                } else {
                    items_localization.toList()
                }
            )
        }

        ///////////////////////////////////////////////////////////

        val items_buildingElement = listOf("", "EG", "1. OG", "2. OG", "3. OG", "KG")
        val selectedItem_buildingElement = "" // Setze hier den ausgewählten Wert
        val label_buildingElement = "Select a Building-Element" // Setze hier den Label-Text

        var expanded_buildingElement by remember { mutableStateOf(false) }
        var listItems_buildingElement by remember(items_localization, selectedItem_localization) {
            mutableStateOf(
                if (selectedItem_buildingElement.isNotEmpty()) {
                    items_buildingElement.filter { x -> x.startsWith(selectedItem_localization.lowercase(), ignoreCase = true) }
                } else {
                    items_buildingElement.toList()
                }
            )
        }

        var listItemsNew_buildingElement by remember(items_localization, selectedItem_localization) {
            mutableStateOf(
                if (selectedItem_buildingElement.isNotEmpty()) {
                    items_buildingElement.filter { x -> x.startsWith(selectedItem_localization.lowercase(), ignoreCase = true) }
                } else {
                    items_buildingElement.toList()
                }
            )
        }


        //////////////////////////////////////////////////////////////
        fun getLocalizationPathById(id: Int? ): String{


            val path = StringBuilder()
            var currentId = id

            while (currentId != null) {
                val localization = dao.getLocalizationByIdOnce(currentId)
                if (localization != null) {
                    Log.d(TAG, "localization:  " +localization.name  )
                }else{
                    Log.d(TAG, "localization nicht gefunden: $currentId")
                }

                if (localization != null) {
                    path.insert(0, localization.name)
                    if (localization.rootLocalizationId != null) {
                        path.insert(0, " > ")
                    }
                    currentId = localization.rootLocalizationId!!
                } else {
                    break
                }
            }
            return path.toString()
        }

        dao.getLocalizationById(localizationId.value).observe(
            LocalLifecycleOwner.current,
            Observer{localization ->
                if (localization!= null){
                    localizationName = localization.name
                    rootId = localization.rootLocalizationId
                    localizationLevel = localization.numberOfLevel
                    coroutineScope.launch (Dispatchers.IO){
                        try {
                            path = getLocalizationPathById(localization.localizationId)
                            Log.d(TAG, "Der aktuelle Pfad" + path)
                            // Verarbeite die Daten hier
                        } catch (e: Exception) {
                            // Behandle den Fehler
                            Log.e("MyViewModel", "Datenbankabfrage fehlgeschlagen: $e")
                        }}
                }
                else {
                    localizationName = null
                    path = ""
                    localizationLevel = 0
                }
            }
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

        Log.d("", localizationSubs.toString())


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
/////////////////////////////////////////////////////////////////
        var localizationInput by remember {
            mutableStateOf("")
        }
        var buildingElementInput by remember {
            mutableStateOf("")
        }

        ////////////////////////////////////////////////////////////////

        Column (  modifier = Modifier
            .height(780.dp)
            .width(400.dp)
            .padding(5.dp)
            .background(Color.Cyan)
            //.verticalScroll(rememberScrollState()).fillMaxSize(),
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Column(  modifier = Modifier
                .height(55.dp)
                .width(400.dp)
                .padding(5.dp)
                .background(Color.LightGray)
                //.verticalScroll(rememberScrollState()).fillMaxSize(),
                ,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Row (modifier = Modifier
                    .width(400.dp)
                    .height(40.dp)
                ){
                    Button(onClick = {
                        onNavigateDamage()}) {
                        Text(text = "Zu Schäden", fontSize = 14.sp)
                    }
                }
            }


            Column(
                modifier = Modifier
                    .height(700.dp)
                    .width(400.dp)
                    .padding(5.dp)
                    .background(Color.Green)
                    .verticalScroll(rememberScrollState()),
                //.verticalScroll(rememberScrollState()).fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier
                    .height(3.dp))

                Row (modifier = Modifier
                    .width(350.dp)
                    .height(60.dp)
                ){
                    Column (modifier = Modifier
                        .width(320.dp)
                        .background(Color.Yellow)){
                        Text(text = localizationName ?: "Liegenschaft")  //"?:" bedeutet, wenn der Wert link null ist, dann nehmen wir stattdessen den rechten Wert "Liegenschaft".

                        val pic1 = "He"
                        val pic2 = "llo World"
                        val pic3 = pic1 + pic2
                        Text(path?:"LIEGENSCHAFT")
                        Log.d(TAG, path?:"LIEGENSCHAFT")
                        Log.d(TAG, localizationId.value.toString())
                        //Text("")
                    }

                    IconButton(onClick = {
                        localizationId.value = rootId

                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(30.dp),
                            tint= AppBlue,)
                    }
                }

                //////////////////////////////////////////////////////
                // Der neue Code für das Texteingabefeld.

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )

                {
                    Row {

                        Column (modifier = Modifier
                                .width(220.dp)
                            .background(Color.Yellow)){

                            ExposedDropdownMenuBox(
                                expanded = expanded_localization,
                                onExpandedChange = {
                                    expanded_localization = !expanded_localization
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Red)
                            ) {

                                //LaunchedEffect(selectedItem) {
                                //    selectedText = selectedItem
                                //}

                                LaunchedEffect(selectedItem_localization) {
                                    //selectedText = selectedItem
                                    localizationInput = selectedItem_localization
                                }

                                OutlinedTextField(
                                    //readOnly = true,
                                    //value = selectedText,
                                    value = localizationInput,
                                    //value = "Wähle die Localization",
                                    label = { Text(label_localization) },
                                    onValueChange = {
                                        //localizationInput = ""
                                        //localizationInput = localizationInput
                                        if (!expanded_localization) {
                                            expanded_localization = true
                                        }
                                        //selectedText = it
                                        localizationInput = it

                                        listItems_localizazion = if (it.isNotEmpty()) {
                                            items_localization.filter { x -> x.startsWith(it.lowercase(), ignoreCase = true) }
                                        } else {
                                            items_localization.toList()
                                        }
                                    },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded_localization) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor()
                                )

                                Log.d(TAG, "TEXT AUS TEXTEINGABEFELD:   " + localizationInput)

                                ExposedDropdownMenu(
                                    expanded = expanded_localization,
                                    onDismissRequest = { expanded_localization = false }  // false
                                ) {
                                    if (listItems_localizazion.isEmpty()) {
                                        DropdownMenuItem(
                                            text = { Text(text = "No items found") },
                                           onClick = {
                                                expanded_localization = false
                                            }
                                        )
                                    } else {
                                        listItems_localizazion.forEach { item ->
                                            DropdownMenuItem(
                                                text = { Text(text = item) },
                                                onClick = {
                                                   //selectedText = item
                                                    localizationInput = item
///////////////////////////////////////////////////////////////////////////////////
                                                    //expanded = false
                                                    expanded_localization = false
                                                    //onItemSelected(item)
                                                }
                                            )
                                        }
                                    }
                                }
                                //Log.d(TAG, selectedText)
                                //localizationInput = selectedText
                            }

                        }

                        Column (
                            modifier = Modifier
                                .height(80.dp)
                                .width(50.dp)
                        ){

                            IconButton(onClick = {
                                var l: Localization = Localization(
                                    name = localizationInput,
                                    rootLocalizationId = localizationId.value,
                                    numberOfLevel = localizationLevel + 1
                                );

                                Log.d(TAG, "LEVEL VON NEUEM ELEMENT:" + l.numberOfLevel.toString())
                                onEvent(LocalizationEvent.SaveLocalization(l))
                                localizationInput = ""

                                //selectedText = ""
                                //cleanTextInput = ""
                                //listItems = listItems
                                //selectedItem = ""

                              listItems_localizazion =listItemsNew_localization
                            }) {
                                Icon(imageVector = Icons.Default.Add,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(40.dp),
                                    tint= AppBlue,)
                            }
                        }
                    }
                }

///////////////////////////////////////////////////////////////////

                Spacer(modifier = Modifier
                    .height(2.dp))


                Column (modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .height(220.dp)
                    .width(330.dp)
                    //.height(28.dp)
                    .background(AppBlue)){
                    LazyColumn(modifier = Modifier
                        .clip(shape = RoundedCornerShape(0.5.dp))
                        .padding(1.5.dp)
                        .clip(RoundedCornerShape(1.5.dp))

                        .padding(1.5.dp)
                        //.border(2.dp, color = Color.Red)
                        .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

                    ){ items(items=localizationSubs)
                    {


                        Box(modifier = Modifier
                            .clickable { localizationId.value = it.localizationId }
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
                                        Text(text = ""+it.name
                                            //+ it.number,
                                            ,fontSize = 18.sp,
                                            color = AppBlue
                                        )
                                    }

                                    Column  (
                                        modifier = Modifier
                                            .height(50.dp)
                                            .width(45.dp)
                                    ){
                                        IconButton(onClick = {
                                            onEvent(LocalizationEvent.DeleteLocalization(it))

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
                                            onEvent(LocalizationEvent.DeleteLocalization(it))

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
                ////////////////////////////////////////////////////
                // entry building element

                Spacer(modifier = Modifier
                    .height(3.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp, vertical = 2.dp)
                )

                {
                    Row {

                        Column (modifier = Modifier
                            .width(220.dp)
                            .background(Color.Yellow)){



                            ExposedDropdownMenuBox(
                                expanded = expanded_buildingElement,
                                onExpandedChange = {
                                    expanded_buildingElement = !expanded_buildingElement
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray)
                            ) {

                                //LaunchedEffect(selectedItem) {
                                //    selectedText = selectedItem
                                //}

                                LaunchedEffect(selectedItem_buildingElement) {
                                    //selectedText = selectedItem
                                    buildingElementInput = selectedItem_buildingElement
                                }


                                OutlinedTextField(
                                    //readOnly = true,
                                    //value = selectedText,
                                    value = buildingElementInput,
                                    //value = "Wähle die Localization",



                                    label = { Text(label_buildingElement) },
                                    onValueChange = {
                                        //localizationInput = ""
                                        //localizationInput = localizationInput
                                        if (!expanded_buildingElement) {
                                            expanded_buildingElement = true
                                        }
                                        //selectedText = it
                                        buildingElementInput = it

                                        listItems_buildingElement = if (it.isNotEmpty()) {
                                            items_buildingElement.filter { x -> x.startsWith(it.lowercase(), ignoreCase = true) }
                                        } else {
                                            items_buildingElement.toList()
                                        }
                                    },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded_buildingElement) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor()
                                )

                                Log.d(TAG, "TEXT AUS TEXTEINGABEFELD:   " + localizationInput)

                                ExposedDropdownMenu(
                                    expanded = expanded_buildingElement,
                                    onDismissRequest = { expanded_buildingElement = false }  // false
                                ) {
                                    if (listItems_buildingElement.isEmpty()) {
                                        DropdownMenuItem(
                                            text = { Text(text = "No items found") },
                                            onClick = {
                                                expanded_buildingElement = false
                                            }
                                        )
                                    } else {
                                        listItems_buildingElement.forEach { item ->
                                            DropdownMenuItem(
                                                text = { Text(text = item) },
                                                onClick = {
                                                    buildingElementInput = item
                                                    expanded_buildingElement = false
                                                }
                                            )
                                        }
                                    }
                                }
                                //Log.d(TAG, selectedText)
                                //localizationInput = selectedText
                            }
                        }

                        //////////////////////////////////// add building element

                        Column (
                            modifier = Modifier
                                .height(80.dp)
                                .width(50.dp)
                        ){

                            IconButton(onClick = {
                                var l: Localization = Localization(
                                    name = localizationInput,
                                    rootLocalizationId = localizationId.value,
                                    numberOfLevel = localizationLevel + 1
                                );

                                Log.d(TAG, "LEVEL VON NEUEM ELEMENT:" + l.numberOfLevel.toString())
                                onEvent(LocalizationEvent.SaveLocalization(l))
                                localizationInput = ""

                                //selectedText = ""
                                //cleanTextInput = ""
                                //listItems = listItems
                                //selectedItem = ""

                                listItems_localizazion =listItemsNew_localization


                            }) {
                                Icon(imageVector = Icons.Default.Add,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(40.dp),
                                    tint= AppBlue,)
                            }
                        }
                    }

                }

                Box(modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .background(Color.Yellow),
                    contentAlignment = Alignment.Center){
                    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {

                        TextField(value = typeOfInspection, onValueChange = {},
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
                                text = { Text(text = "Brandschutztür-Überprüfung") },
                                onClick = { typeOfInspection = "Brandschutztür-Überprüfung"
                                    isExpanded = false })

                            DropdownMenuItem(
                                text = { Text(text = "Feuerlöscher-Überprüfung") },
                                onClick = { typeOfInspection = "Feuerlöscher-Überprüfung"
                                    isExpanded = false })

                            DropdownMenuItem(
                                text = { Text(text = "Dachsicherungssystem-Überprüfung") },
                                onClick = { typeOfInspection = "Dachsicherungssystem-Überprüfung"
                                    isExpanded = false })

                        }
                    }


                }





            ////////////////////////////////////////////////////
            }
        }
    }}


