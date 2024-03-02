package com.example.a09_damage_application.componets

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.AddressEvent
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.interfaces.AddressDao
import com.example.a09_damage_application.ui.theme.AppBackground
import com.example.a09_damage_application.ui.theme.AppDarkGreen
import kotlinx.coroutines.launch

class AddressCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun AddressCreationComposable(dao: AddressDao) {
        //var text by rememberSaveable { mutableStateOf("") }

        val coroutineScope = rememberCoroutineScope()

        var addressId: Int? = null

        var addButtonText by remember {
            mutableStateOf("Kontakt hinzufügen")
        }


        var streetInput by remember {
            mutableStateOf("")
        }

        var streetNumberInput by remember {
            mutableStateOf("")
        }

        var postalCodeInput by remember {
            mutableStateOf("")
        }

        var cityInput by remember {
            mutableStateOf("")
        }

        var countryInput by remember {
            mutableStateOf("")
        }

        var addressList = remember {
            mutableStateListOf<Address>() // Hier kann man auch schon Einträge übergeben.
        }

        dao.getAddressesOrderedByTitle().observe(
            LocalLifecycleOwner.current,
            Observer { allAddresses ->
                addressList.clear()
                addressList.addAll(allAddresses)
            })

        fun onEvent(event: AddressEvent) {
            when (event) {
                is AddressEvent.SaveAddress -> {
                    coroutineScope.launch { dao.upsertAddress(event.address) }
                }

                is AddressEvent.DeleteAddress -> {
                    coroutineScope.launch { dao.deleteAddress(event.address) }
                }

                else -> {}
            }
        }

        //val scrollState = rememberScrollState()





            Column(
                modifier = Modifier
                    .width(280.dp)
                    .background(AppDarkGreen)

                    .width(320.dp),
                //.verticalScroll(state = scrollState)
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



                fun addAddress(contactId: Int?) {
                    var f: Address = Address(
                        addressId = addressId,
                        street = streetInput,
                        streetNumber = streetNumberInput,
                        postalCode = postalCodeInput,
                        city = cityInput,
                        country = countryInput
                    );// Es wird ein neues Objekt der Klasse Address erzeugt.
                    onEvent(AddressEvent.SaveAddress(f))

                    addressId = null; streetInput = "";streetNumberInput = "";postalCodeInput =
                        "";cityInput = ""; countryInput = ""; addButtonText = "Kontakt hinzufügen"
                }
                {

                }

                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                        .width(150.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(text = "Anschrift:", fontWeight = FontWeight.Medium)
                }

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                var streetText = "Straße"
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    value = streetInput,
                    onValueChange = { streetInput = it },
                    label = { Text(streetText) },
                    singleLine = true
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    value = streetNumberInput,
                    onValueChange = { streetNumberInput = it },
                    label = { Text("Hausnummer") },
                    singleLine = true
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    value = postalCodeInput,
                    onValueChange = { postalCodeInput = it },
                    label = { Text("Postleitzahl") },
                    singleLine = true
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    value = cityInput,
                    onValueChange = { cityInput = it },
                    label = { Text("Ort") },
                    singleLine = true,
                    //OutlineTextFieldDesign().outlineTextFieldDesign

                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    value = countryInput,
                    onValueChange = { countryInput = it },
                    label = { Text("Land") },
                    singleLine = true,
                )
                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                )



                /*
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        //.height(28.dp)
                        .background(AppBlue)
                ) {
                    LazyColumn(
                        modifier = Modifier

                            .padding(1.5.dp)
                            //.border(2.dp, color = Color.Red)
                            .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

                    ) {
                        items(items = addressList) {


                            Box(
                                BoxRounded().boxRounded
                            ) {
                                Column {
                                    Row {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Text(
                                                text = "" + it.street
                                                //+ it.number,
                                                , fontSize = 18.sp
                                            )
                                            Text(
                                                text = "" + it.streetNumber
                                                //+ it.number,
                                                , fontSize = 18.sp
                                            )
                                            Text(
                                                text = "" + it.postalCode
                                                //+ it.number,
                                                , fontSize = 18.sp
                                            )
                                            Text(
                                                text = "" + it.city
                                                //+ it.number,
                                                , fontSize = 18.sp
                                            )
                                            Text(
                                                text = "" + it.country
                                                //+ it.number,
                                                , fontSize = 18.sp,
                                                color = Pink40
                                            )

                                        }

                                        Column {
                                            Box(
                                                modifier = Modifier
                                                    .width(30.dp)
                                                    .heightIn(30.dp),
                                                contentAlignment = Alignment.Center
                                            )
                                            {
                                                BadgedBox(badge = {

                                                }
                                                )
                                                {
                                                    IconButton(onClick = {
                                                        onEvent(
                                                            AddressEvent.DeleteAddress(
                                                                it
                                                            )
                                                        )
                                                    }) {
                                                        Icon(
                                                            imageVector = Icons.Default.Delete,
                                                            contentDescription = "Favorite",
                                                            modifier = Modifier.size(40.dp),
                                                            tint = AppBlue,
                                                        )
                                                    }
                                                }
                                            }

                                            Box(
                                                modifier = Modifier
                                                    .width(30.dp)
                                                    .heightIn(30.dp),
                                                contentAlignment = Alignment.Center
                                            )
                                            {
                                                BadgedBox(badge = {

                                                }
                                                )
                                                {
                                                    IconButton(onClick = {
                                                        streetInput = it.street;postalCodeInput =
                                                        it.postalCode; addressId =
                                                        it.addressId; addButtonText =
                                                        "Änderungen speichern"
                                                    }) {
                                                        Icon(
                                                            imageVector = Icons.Default.Create,
                                                            contentDescription = "Favorite",
                                                            modifier = Modifier.size(40.dp),
                                                            tint = AppBlue,
                                                        )

                                                    }

                                                }
                                            }

                                        }

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

                                        onEvent(AddressEvent.DeleteAddress(it))

                                        },
                                            colors = ButtonDefaults.buttonColors
                                            (contentColor = Color.White,   //pre-created colour
                                        containerColor = AppBlue),

                                    modifier = Modifier
                                        .size(170.dp, 40.dp))

                                    {
                                        Text(text = "Delete", fontSize = 12.sp)
                                    }*/


                                    /*

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
                                            Icon(imageVector = Icons.Default.Delete,
                                                contentDescription = "Favorite",
                                                modifier = Modifier.size(40.dp))
                                        }
                                    }*/

                                }
                            }
                        }
                    }
                }*/

                /////////////////////////////////////////////////////////////



                /*
                                Column (modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .fillMaxWidth()
                                    //.height(28.dp)
                                    .background(AppBlue)){
                                    LazyColumn(modifier = Modifier

                                        .padding(1.5.dp)
                                        //.border(2.dp, color = Color.Red)
                                        .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

                                    ){ items(items=addressList) {


                                        Box(
                                            BoxRounded().boxRounded
                                        ){
                                            Column {
                                                Row {
                                                    Column (
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                    ){
                                                        Text(text = ""+it.street
                                                            //+ it.number,
                                                            ,fontSize = 18.sp)
                                                        Text(text = ""+it.streetNumber
                                                            //+ it.number,
                                                            ,fontSize = 18.sp)
                                                        Text(text = ""+it.postalCode
                                                            //+ it.number,
                                                            ,fontSize = 18.sp)
                                                        Text(text = ""+it.city
                                                            //+ it.number,
                                                            ,fontSize = 18.sp)
                                                        Text(text = ""+it.country
                                                            //+ it.number,
                                                            ,fontSize = 18.sp,
                                                            color = Pink40)

                                                }

                                                    Column {
                                                        Box(modifier = Modifier
                                                            .width(30.dp)
                                                            .heightIn(30.dp),
                                                            contentAlignment = Alignment.Center)
                                                        {
                                                            BadgedBox(badge = {

                                                            }
                                                            )
                                                            {
                                                                IconButton(onClick = {onEvent(AddressEvent.DeleteAddress(it)) }) {
                                                                    Icon(imageVector = Icons.Default.Delete,
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
                                                                IconButton(onClick = { streetInput = it.street;postalCodeInput = it.postalCode; addressId = it.id; addButtonText = "Änderungen speichern"}) {
                                                                    Icon(imageVector = Icons.Default.Create,
                                                                        contentDescription = "Favorite",
                                                                        modifier = Modifier.size(40.dp),
                                                                        tint= AppBlue,)

                                                                }

                                                            }
                                                        }

                                                    }

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

                                                    onEvent(AddressEvent.DeleteAddress(it))

                                                    },
                                                        colors = ButtonDefaults.buttonColors
                                                        (contentColor = Color.White,   //pre-created colour
                                                    containerColor = AppBlue),

                                                modifier = Modifier
                                                    .size(170.dp, 40.dp))

                                                {
                                                    Text(text = "Delete", fontSize = 12.sp)
                                                }*/



                                                /*

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
                                                        Icon(imageVector = Icons.Default.Delete,
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
                            }*/

                Log.d(TAG, "Schadensliste: " + addressList.joinToString())

                //Text(text = "Hallo Welt")

                var text by rememberSaveable { mutableStateOf("") }
            }
        }
    }
