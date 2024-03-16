package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.a09_damage_application.ui.theme.AppBlue

class OwnerListCreationComponent {
    @ExperimentalMaterial3Api
    @Composable

    fun OwnerListComposable(
        onNavigateDamage: () -> Unit,
        onNavigateOwner: ()->Unit
    ){

        Column(
            modifier = Modifier
                .width(300.dp)
                .height(600.dp)


                //.height(28.dp)
                .background(AppBlue)
        ) {
            LazyColumn(
                modifier = Modifier

                    .padding(1.5.dp)
                    //.border(2.dp, color = Color.Red)
                    .background(Color.Red)

            ) {/*
                items(items = ownerList) {


                    Box(

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
                        }
                    }
                }
            }*/

        /*Column (modifier = Modifier


                    .width(300.dp)
                    .height(700.dp)
                    .padding(1.dp)
                    .background(Color.Yellow)
                    .verticalScroll(rememberScrollState()),



            verticalArrangement = Arrangement.Top,)
           {
               Box(modifier = Modifier
                   .width(50.dp)
                   .height(50.dp)
                   .background(Color.Red))





    }*/




}}}}
