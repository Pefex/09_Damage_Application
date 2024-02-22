package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.AddressEvent
import com.example.a09_damage_application.componets.events.ContactEvent
import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.entities.Contact
import com.example.a09_damage_application.data.interfaces.AddressDao
import com.example.a09_damage_application.data.interfaces.ContactDao
import com.example.a09_damage_application.data.interfaces.ContactWithAddressDao
import com.example.a09_damage_application.ui.theme.AppBackground
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded
import com.example.a09_damage_application.ui.theme.Pink40
import kotlinx.coroutines.launch

class ContactCreationComponent {

    @ExperimentalMaterial3Api
    @Composable
    fun ContactCreationComposable(
        contactDao: ContactDao,
        addressDao: AddressDao,


    ) {

        val coroutineScope = rememberCoroutineScope()

        var contactId: Int? = null

        var addButtonText by remember {
            mutableStateOf("Kontakt hinzufügen")
        }

        var telephoneNumberMobilInput by remember {
            mutableStateOf("")
        }

        var telephoneNumberLandlineInput by remember {
            mutableStateOf("")
        }

        var mailaddressInput by remember {
            mutableStateOf("")
        }

        var contactList = remember {
            mutableStateListOf<Contact>()
        }

        contactDao.getContactOrderByTitle().observe(LocalLifecycleOwner.current,
            Observer { allContacts ->
                contactList.clear()
                contactList.addAll(allContacts)
            })

        fun onEvent(event: ContactEvent) {
            when (event) {
                is ContactEvent.SaveContact -> {
                    coroutineScope.launch { contactDao.upsertContact(event.contact) }
                }

                is ContactEvent.DeleteContact -> {
                    coroutineScope.launch { contactDao.deleteContact(event.contact) }
                }

                else -> {}
            }
        }

        Column(
            modifier = Modifier


                .fillMaxSize()
                .height(100.dp)
                .padding(1.dp)
                .background(AppBackground),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier

                    .width(320.dp),
                //.verticalScroll(state = scrollState)
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
/*
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)


                ){
                    Button(onClick = {onNavigateDamage()},

                        colors = ButtonDefaults.buttonColors
                            (contentColor = Color.White,   //pre-created colour
                            containerColor = AppBlue
                        ),

                        modifier = Modifier
                            .size(120.dp, 40.dp)){

                        Text(text = "Zu Damage", fontSize = 12.sp)
                    }
                    Button(onClick = {onNavigateName()},

                        colors = ButtonDefaults.buttonColors
                            (contentColor = Color.White,   //pre-created colour
                            containerColor = AppBlue
                        ),

                        modifier = Modifier
                            .size(120.dp, 40.dp)){

                        Text(text = "Zu Name", fontSize = 12.sp)
                    }

                }*/

                val addressCreation = AddressCreationComponent().AddressCreationComposable(
                    dao = addressDao,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align((Alignment.Start))
                ) {
                    Text(text = "Telefonnummer", fontWeight = FontWeight.Medium)
                }

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )


                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    value = telephoneNumberMobilInput,
                    onValueChange = { telephoneNumberMobilInput = it },
                    label = { Text("Handynummer") },
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
                    value = telephoneNumberLandlineInput,
                    onValueChange = { telephoneNumberLandlineInput = it },
                    label = { Text("Festnetznummer") },
                    singleLine = true
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align((Alignment.Start))
                ) {
                    Text(text = "Mailadresse", fontWeight = FontWeight.Medium)
                }

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    value = mailaddressInput,
                    onValueChange = { mailaddressInput = it },
                    label = { Text("Mailadresse") },
                    singleLine = true
                )

                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                )

                fun addContact() {
                    // Es wird ein neues Objekt der Klasse Address erzeugt.
                    //To do addressCreation.
                    var c: Contact = Contact(
                        contactId = contactId,
                        telephoneNumberMobil = telephoneNumberMobilInput,
                        telephoneNumberLandline = telephoneNumberLandlineInput,
                        isBusinessContact = false, //TODO
                        businessName = null, //TODO
                        mailAddress = mailaddressInput,
                        contactAddressId = null, //TODO
                        );
                    onEvent(ContactEvent.SaveContact(c))

                    contactId = null; telephoneNumberMobilInput = "";telephoneNumberLandlineInput =
                        "";mailaddressInput = ""
                }



                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                        .width(150.dp)
                )/*
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
                        items(items = contactList) {


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
                                                text = "" + it.telephoneNumberMobil
                                                //+ it.number,
                                                , fontSize = 18.sp
                                            )
                                            Text(
                                                text = "" + it.telephoneNumberLandline
                                                //+ it.number,
                                                , fontSize = 18.sp
                                            )
                                            Text(
                                                text = "" + it.mailAddress
                                                //+ it.number,
                                                , fontSize = 18.sp
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
                                                            ContactEvent.DeleteContact(
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



                                        }

                                    }


                                }
                            }
                        }
                    }*/
            }


        }

    }


}

