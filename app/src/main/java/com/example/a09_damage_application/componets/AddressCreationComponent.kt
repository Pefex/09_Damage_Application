package com.example.a09_damage_application.componets

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.AddressEvent
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.interfaces.AddressDao
import com.example.a09_damage_application.ui.theme.BoxRounded
import kotlinx.coroutines.launch

class AddressCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun AddressCreationComposable(dao: AddressDao){
        //var text by rememberSaveable { mutableStateOf("") }

        val coroutineScope = rememberCoroutineScope()

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

        fun onEvent(event: AddressEvent){
            when(event){
                is AddressEvent.SaveAddress -> {
                    coroutineScope.launch{ dao.upsertAddress(event.address) }
                }
                is AddressEvent.DeleteAddress -> {
                    coroutineScope.launch{ dao.deleteAddress(event.address) }
                }
                else -> {}
            }
        }



        Column (modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
            .padding(2.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            TextField(
                value = streetInput,
                onValueChange = { streetInput = it },
                label = { Text("Straße") },
                singleLine = true
            )
            TextField(
                value = streetNumberInput,
                onValueChange = { streetNumberInput = it },
                label = { Text("Hausnummer") },
                singleLine = true
            )
            TextField(
                value = postalCodeInput,
                onValueChange = { postalCodeInput = it },
                label = { Text("Postleitzahl") },
                singleLine = true
            )
            TextField(
                value = cityInput,
                onValueChange = { cityInput = it },
                label = { Text("Ort") },
                singleLine = true
            )
            TextField(
                value = countryInput,
                onValueChange = { countryInput = it },
                label = { Text("Land") },
                singleLine = true
            )

            Button(onClick = {
                var f: Address = Address(
                    street = streetInput ,
                    streetNumber = streetNumberInput,
                    postalCode = postalCodeInput,
                    city = cityInput,
                    country = countryInput
                ); ;// Es wird ein neues Objekt der Klasse Damage erzeugt.
                onEvent(AddressEvent.SaveAddress(f))

                streetInput = "" ;streetNumberInput = "" ;postalCodeInput ="";cityInput = ""; countryInput = ""}, modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)) {
                Text(text = "Add to List", fontSize = 28.sp)
            }

            Column (modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .height(280.dp)
                .background(Color(37, 150, 190, 150))){
                LazyColumn(modifier = Modifier

                    .padding(1.5.dp)
                    //.border(2.dp, color = Color.Red)
                    .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

                ){ items(items=addressList) {


                    Box(
                        BoxRounded().boxRounded
                    ){
                        Column {
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
                                ,fontSize = 18.sp)
                        }
                    }
                }
                }
            }


        }

        Log.d(TAG, "Schadensliste: " + addressList.joinToString())
        
        Text(text = "Hallo Welt")





        var text by rememberSaveable { mutableStateOf("") }



    }
}