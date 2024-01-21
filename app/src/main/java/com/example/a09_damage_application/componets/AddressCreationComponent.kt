package com.example.a09_damage_application.componets

import android.content.ContentValues.TAG
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.R
import com.example.a09_damage_application.componets.events.AddressEvent
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.interfaces.AddressDao
import com.example.a09_damage_application.ui.theme.AppBackground
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded
import com.example.a09_damage_application.ui.theme.OutlineTextFieldDesign
import com.example.a09_damage_application.ui.theme.Pink40
import com.example.a09_damage_application.ui.theme.Purple40
import com.example.a09_damage_application.ui.theme.PurpleGrey40
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

        val scrollState = rememberScrollState()

        Column (modifier = Modifier


            .fillMaxSize()
            .height(100.dp)
            .padding(1.dp)
            .background(AppBackground),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){

            Column (modifier = Modifier

                .width(300.dp),
                //.verticalScroll(state = scrollState)
                horizontalAlignment = Alignment.CenterHorizontally){
                Row (modifier = Modifier
                    .width(300.dp)
                    .align((Alignment.Start))
                ){
                    Text(text = "Anschrift",  fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier
                    .height(20.dp))


                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .width(300.dp),
                    value = streetInput,
                    onValueChange = { streetInput = it },
                    label = { Text("Straße") },
                    singleLine = true
                )
                Spacer(modifier = Modifier
                    .height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .width(300.dp),
                    value = streetNumberInput,
                    onValueChange = { streetNumberInput = it },
                    label = { Text("Hausnummer") },
                    singleLine = true
                )
                Spacer(modifier = Modifier
                    .height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .width(300.dp),
                    value = postalCodeInput,
                    onValueChange = { postalCodeInput = it },
                    label = { Text("Postleitzahl") },
                    singleLine = true
                )
                Spacer(modifier = Modifier
                    .height(10.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .width(300.dp),
                    value = cityInput,
                    onValueChange = { cityInput = it },
                    label = { Text("Ort") },
                    singleLine = true,
                    //OutlineTextFieldDesign().outlineTextFieldDesign

                )
                Spacer(modifier = Modifier
                    .height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .width(300.dp),
                    value = countryInput,
                    onValueChange = { countryInput = it },
                    label = { Text("Land") },
                    singleLine = true,
                )
                Spacer(modifier = Modifier
                    .height(40.dp))

                Button(


                    onClick = {
                    var f: Address = Address(
                        street = streetInput ,
                        streetNumber = streetNumberInput,
                        postalCode = postalCodeInput,
                        city = cityInput,
                        country = countryInput
                    ); ;// Es wird ein neues Objekt der Klasse Damage erzeugt.
                    onEvent(AddressEvent.SaveAddress(f))

                    streetInput = "" ;streetNumberInput = "" ;postalCodeInput ="";cityInput = ""; countryInput = ""},
                    colors = ButtonDefaults.buttonColors
                        (contentColor = Color.White,   //pre-created colour
                        containerColor = AppBlue),

                    modifier = Modifier
                    .size(170.dp, 40.dp)

                ) {
                    Text(text = "Kontakt hinzufügen", fontSize = 12.sp)
                }

                Spacer(modifier = Modifier
                    .height(40.dp)
                    .width(150.dp))


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
                                } } } } } } } }

        Log.d(TAG, "Schadensliste: " + addressList.joinToString())
        
        Text(text = "Hallo Welt")

        var text by rememberSaveable { mutableStateOf("") }
    }
}