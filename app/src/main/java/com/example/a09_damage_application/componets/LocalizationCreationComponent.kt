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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a09_damage_application.componets.events.AddressEvent
import com.example.a09_damage_application.data.interfaces.LocalizationDao
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded

class LocalizationCreationComponent {
    @ExperimentalMaterial3Api
    @Composable
    fun DamageLocalizationCreationComposable(
        dao: LocalizationDao,
        localizationId: Int? = null,
        onNavigateDamage: () ->Unit,
        onNavigateOwner: () ->Unit
    ) {

        val numbers = listOf("Felix", "Peter", "Walter")





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
                    onNavigateOwner()}) {
                    Text(text = "Zu Eigentümer", fontSize = 14.sp)

                }

                Button(onClick = {onNavigateDamage()}) {
                    Text(text = "Zu Damage", fontSize = 14.sp)

                }

            }


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

                ){ items(items=numbers) {


                    Box(
                        BoxRounded().boxRounded
                    ){
                        Column {
                            Row {
                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ){
                                    Text(text = ""+it
                                        //+ it.number,
                                        ,fontSize = 18.sp)

                                    Text(text = ""+it
                                        //+ it.number,
                                        ,fontSize = 18.sp,
                                        color = AppBlue
                                    )

                                }

                                Column {
                                    Box(modifier = Modifier
                                        .width(30.dp)
                                        .heightIn(20.dp),
                                        contentAlignment = Alignment.Center)
                                    {
                                        BadgedBox(badge = {

                                        }
                                        )
                                        {
                                            IconButton(onClick = {}) {
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

                                }

                            }


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
                            }

                        }
                    }
                }
                }
            }


        }

    }}


