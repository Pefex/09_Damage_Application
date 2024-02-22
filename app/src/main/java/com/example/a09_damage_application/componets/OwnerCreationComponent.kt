package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.OwnerEvent
import com.example.a09_damage_application.data.combinedData.ContactWithAddress
import com.example.a09_damage_application.data.combinedData.OwnerWithContacts
import com.example.a09_damage_application.data.interfaces.AddressDao
import com.example.a09_damage_application.data.interfaces.ContactDao
import com.example.a09_damage_application.data.interfaces.ContactWithAddressDao
import com.example.a09_damage_application.data.interfaces.OwnerDao
import com.example.a09_damage_application.ui.theme.AppBackground
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded
import kotlinx.coroutines.launch

class OwnerCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun OwnerCreationComposable(
        ownerDao: OwnerDao,
        contactWithAddressDao: ContactWithAddressDao,
        addressDao: AddressDao,
        contactDao: ContactDao
    ){
        val coroutineScope = rememberCoroutineScope()

        var ownerList = remember {
            mutableStateListOf<OwnerWithContacts>() // Hier kann man auch schon Einträge übergeben.
        }

        ownerDao.getOwnerOrderByTitle().observe(
            LocalLifecycleOwner.current,
            Observer { allOwners ->
                ownerList.clear()

                for (owner in allOwners) {
                    var privateContact: ContactWithAddress? = null
                    var businessContact: ContactWithAddress? = null
                    if (owner.privateContactId != null){
                        privateContact = contactWithAddressDao.getContactWithAddress(owner.privateContactId).value
                    }
                    if (owner.businessContactId != null){
                        businessContact = contactWithAddressDao.getContactWithAddress(owner.businessContactId).value
                    }
                    var ownerWithContacts = OwnerWithContacts(
                        owner.ownerId!!,
                        privateContact,
                        businessContact
                    )
                    ownerList.add(ownerWithContacts)
                }

            })

        fun onEvent(event: OwnerEvent){
            when(event){
                is OwnerEvent.SaveOwner -> {
                    coroutineScope.launch{ ownerDao.upsertOwner(event.owner) }
                }
                is OwnerEvent.DeleteOwner -> {
                    coroutineScope.launch{ ownerDao.deleteOwnerById(event.ownerId) }
                }
                else -> {}
            }
        }

        NameCreationComponent().NameCreationComposable()

        Column (modifier = Modifier


            .fillMaxSize()
            .height(100.dp)
            .padding(1.dp)
            .background(AppBackground)
            //.verticalScroll(rememberScrollState()).fillMaxSize()
            ,

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Column (modifier = Modifier

                .width(300.dp)
                // .verticalScroll(rememberScrollState())

                ,
                //.verticalScroll(state = scrollState)
                horizontalAlignment = Alignment.CenterHorizontally


            ){

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .align((Alignment.Start))
                ){
                    Text(text = "Privatkontakt",  fontWeight = FontWeight.Medium)
                }


            }

        }

        ContactCreationComponent().ContactCreationComposable( contactDao, addressDao)  //Privatadresse

        Column (modifier = Modifier


            .fillMaxSize()
            .height(100.dp)
            .padding(1.dp)
            .background(AppBackground)
            //.verticalScroll(rememberScrollState()).fillMaxSize()
            ,

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Column (modifier = Modifier

                .width(300.dp)
                // .verticalScroll(rememberScrollState())

                ,
                //.verticalScroll(state = scrollState)
                horizontalAlignment = Alignment.CenterHorizontally


            ){

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .align((Alignment.Start))
                ){
                    Text(text = "Firmenkontakt",  fontWeight = FontWeight.Medium)
                }


            }

        }

        ContactCreationComponent().ContactCreationComposable( contactDao, addressDao) // Firmenkontakt



        Column (modifier = Modifier


            .fillMaxSize()
            .height(100.dp)
            .padding(1.dp)
            .background(AppBackground)
            //.verticalScroll(rememberScrollState()).fillMaxSize()
            ,

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Column (modifier = Modifier

                .width(300.dp)
                // .verticalScroll(rememberScrollState())

                ,
                //.verticalScroll(state = scrollState)
                horizontalAlignment = Alignment.CenterHorizontally


            ){


                Button(onClick = { // to do add owner, add contact
                    },
                    modifier = Modifier
                        .size(170.dp, 40.dp)
                ) {

                    Text(text = "Eigentümer hinzufügen", fontSize = 18.sp) }



            }
        }

        // Ausgabe Kontaktliste

        Column (modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth()
            //.height(28.dp)
            .background(AppBlue)){
            LazyColumn(modifier = Modifier

                .padding(1.5.dp)
                //.border(2.dp, color = Color.Red)
                .background(Color(37, 150, 190, 150), shape = RoundedCornerShape(5.dp))

            ){ items(items=ownerList) {


                Box(
                    BoxRounded().boxRounded
                ){
                    Column {
                        Row {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ){
                                Text(text = ""+it.privateContact?.mailAddress
                                    //+ it.number,
                                    ,fontSize = 18.sp)
                                /*
                                Text(text = ""+it.name.firstName
                                    //+ it.number,
                                    ,fontSize = 18.sp)
                                 */
                                Text(text = ""+it.businessContact?.city
                                    //+ it.number,
                                    ,fontSize = 18.sp)

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
                                        IconButton(onClick = {onEvent(OwnerEvent.DeleteOwner(it.ownerId)) }) {
                                            Icon(imageVector = Icons.Default.Delete,
                                                contentDescription = "Favorite",
                                                modifier = Modifier.size(40.dp),
                                                tint= AppBlue,)
                                        }
                                    }
                                }




                            }
                        }
                    }
                }
            }
            }
        }
}
}