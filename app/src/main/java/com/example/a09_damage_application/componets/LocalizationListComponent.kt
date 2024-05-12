package com.example.a09_damage_application.componets

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.LocalizationEvent
import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.data.enums.TypeOfTitle
import com.example.a09_damage_application.data.enums.Type_Of_Level
import com.example.a09_damage_application.data.interfaces.LocalizationDao
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LocalizationListComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun LocalizationListComposable(
        //selectedTypeOfLevel: MutableState<Type_Of_Level>,
        dao: LocalizationDao,  // "dao ist die lokale Variable für das "LocalizationDao"
        rootLocalizationId: Int? = 0,
        onNavigateDamage: () ->Unit,

       // onNavigateLocalization: (id: Int)  ->Unit
        ) {

        //var selectedTypeOfDamage: MutableState<Type_Of_Level>



        ////////////////////////////////////////////////////////////////



        var isExpanded by remember{
            mutableStateOf(false)
        }

        var building_element by remember{
            mutableStateOf("")
        }
        //////////////////////////////////////////////////////////////////

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

        var cleanTextInput = ""

        /////////////////////////////////////////////////////////
        val items = listOf("Apple", "Banana", "Cherry", "Date")
        val selectedItem = "" // Setze hier den ausgewählten Wert
        val label = "Select a fruit" // Setze hier den Label-Text

        var expanded by remember { mutableStateOf(false) }
        var listItems by remember(items, selectedItem) {
            mutableStateOf(
                if (selectedItem.isNotEmpty()) {
                    items.filter { x -> x.startsWith(selectedItem.lowercase(), ignoreCase = true) }
                } else {
                    items.toList()
                }
            )
        }
        var  selectedText by remember(selectedItem) { mutableStateOf(selectedItem) }

        ///////////////////////////////////////////////////////////

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

        var localizationInput by remember {
            mutableStateOf("")
        }

        val numbers = listOf("Felix", "Peter", "Walter", "Frank", "Jürgen")

        Column(
            modifier = Modifier


                .height(800.dp)
                .width(400.dp)
                .padding(5.dp)
                .background(Color.Green)
            //.verticalScroll(rememberScrollState()).fillMaxSize(),
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {

            Spacer(modifier = Modifier
                .height(10.dp))
            Row (modifier = Modifier
                .width(400.dp)
                .height(40.dp)


            ){
                Button(onClick = {
                    onNavigateDamage()}) {
                    Text(text = "Zu Schäden", fontSize = 14.sp)

                }

                IconButton(onClick = {
                    //localizationId.value = rootId
                    //localizationId.value = rootID

                    Log.d("", localizationSubs.toString())
                    //var pathLocalization = getLocalizationPathById()
                }) {
                    Icon(imageVector = Icons.Default.Check,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(30.dp),
                        tint= AppBlue,)
                }

                Column (
                    modifier = Modifier
                        .height(80.dp)
                        .width(250.dp)
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
                        selectedText = ""
                        cleanTextInput = ""

                    }) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(40.dp),
                            tint= AppBlue,)
                    }
                }
            }

            Spacer(modifier = Modifier
                .height(10.dp))

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



            LaunchedEffect(selectedItem) {
                selectedText = selectedItem
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp, vertical = 2.dp)
            )

            {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.Red)
                ) {
                    OutlinedTextField(
                        value = selectedText,
                        label = { Text(label) },
                        onValueChange = {
                            if (!expanded) {
                                expanded = true
                            }
                            selectedText = it
                            listItems = if (it.isNotEmpty()) {
                                items.filter { x -> x.startsWith(it.lowercase(), ignoreCase = true) }
                            } else {
                                items.toList()
                            }
                        },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        if (listItems.isEmpty()) {
                            //DropdownMenuItem(
                            //    text = { Text(text = "No items found") },
                            //   onClick = {
                            //        expanded = false
                            //    }
                            //)
                        } else {
                            listItems.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedText = item
                                        expanded = false
                                        //onItemSelected(item)
                                    }
                                )
                            }
                        }
                    }
                    Log.d(TAG, selectedText)
                    localizationInput = selectedText
                }
            }


/////////////////////////////////////////////////////////////////////
/*
            DropDown(
                items = items,
                onItemSelected = { selectedFruit ->
                    // Hier kannst du die ausgewählte Frucht verarbeiten
                    println("Ausgewählte Frucht: $selectedFruit")
                },
                selectedItem = selectedItem,
                label = label,
                selectedText = cleanTextInput
            )*/


            ///////////////////////////////////////////////////////

// der ursprüngliche Code für das Texteingabefeld
           /* //@OptIn(ExperimentalMaterial3Api::class)
            @ExperimentalMaterial3Api
            @Composable
            fun DropDown(
                items: List<String>,
                onItemSelected: (String) -> Unit,
                selectedItem: String,
                label: String,
                selectedText: String,
                modifier: Modifier = Modifier,
            ) {
                var expanded by remember { mutableStateOf(false) }
                var listItems by remember(items, selectedItem) {
                    mutableStateOf(
                        if (selectedItem.isNotEmpty()) {
                            items.filter { x -> x.startsWith(selectedItem.lowercase(), ignoreCase = true) }
                        } else {
                            items.toList()
                        }
                    )
                }
                var  selectedText by remember(selectedItem) { mutableStateOf(selectedItem) }

                LaunchedEffect(selectedItem) {
                    selectedText = selectedItem
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp, vertical = 2.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        },
                        modifier = Modifier.fillMaxWidth()
                            .background(Color.Red)
                    ) {
                        OutlinedTextField(
                            value = selectedText,
                            label = { Text(label) },
                            onValueChange = {
                                if (!expanded) {
                                    expanded = true
                                }
                                selectedText = it
                                listItems = if (it.isNotEmpty()) {
                                    items.filter { x -> x.startsWith(it.lowercase(), ignoreCase = true) }
                                } else {
                                    items.toList()
                                }
                            },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            if (listItems.isEmpty()) {
                                //DropdownMenuItem(
                                //    text = { Text(text = "No items found") },
                                //   onClick = {
                                //        expanded = false
                                //    }
                                //)
                            } else {
                                listItems.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(text = item) },
                                        onClick = {
                                            selectedText = item
                                            expanded = false
                                            onItemSelected(item)
                                        }
                                    )
                                }
                            }
                        }
                        Log.d(TAG, selectedText)
                        localizationInput = selectedText
                    }
                }
            }

            val items = listOf("Apple", "Banana", "Cherry", "Date")
            val selectedItem = "" // Setze hier den ausgewählten Wert
            val label = "Select a fruit" // Setze hier den Label-Text

            DropDown(
                items = items,
                onItemSelected = { selectedFruit ->
                    // Hier kannst du die ausgewählte Frucht verarbeiten
                    println("Ausgewählte Frucht: $selectedFruit")
                },
                selectedItem = selectedItem,
                label = label,
                selectedText = cleanTextInput
            )*/




            ///////////////////////////////


/*
            Box(modifier = Modifier
                .width(270.dp)
                .height(70.dp)
                .background(Color.White)

            ){
                Column (modifier = Modifier
                    .width(270.dp)){
                    Row {
                        Column (
                            modifier = Modifier
                                .height(70.dp)
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
                    }
                }
            }


            Spacer(modifier = Modifier
                .height(5.dp))

            //////////////////////////////////////////////////////////////

            Box(modifier = Modifier
                .width(270.dp)
                .height(90.dp),
                contentAlignment = Alignment.Center){
                ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {

                    TextField(value = building_element, onValueChange = {},
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
                            text = { Text(text = "Stiege 1") },
                            onClick = { building_element = "Stiege 1";
                                localizationInput = "Stiege 1"

                                isExpanded = false })

                        DropdownMenuItem(
                            text = { Text(text = "Stiege 2") },
                            onClick = { building_element = "Stiege 2";
                                localizationInput = "Stiege 2"
                                isExpanded = false })

                        DropdownMenuItem(
                            text = { Text(text = "Stiege 3") },
                            onClick = { building_element = "Stiege 3";
                                localizationInput = "Stiege 3"
                                isExpanded = false })

                    }
                }
            }*/


            Log.d(ContentValues.TAG,building_element)
///////////////////////////////////////////////////////////////////

            Spacer(modifier = Modifier
                .height(15.dp))


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
    }
}}


