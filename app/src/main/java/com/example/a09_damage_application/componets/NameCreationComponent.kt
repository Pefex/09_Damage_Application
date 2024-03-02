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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.example.a09_damage_application.componets.events.NameEvent
import com.example.a09_damage_application.data.entities.Name
import com.example.a09_damage_application.data.enums.TypeOfGender
import com.example.a09_damage_application.data.enums.TypeOfTitle
import com.example.a09_damage_application.data.interfaces.NameDao
import com.example.a09_damage_application.ui.theme.AppBackground
import com.example.a09_damage_application.ui.theme.AppBlue
import com.example.a09_damage_application.ui.theme.BoxRounded
import kotlinx.coroutines.launch

class NameCreationComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun NameCreationComposable(nameState: Name?) {

        val coroutineScope = rememberCoroutineScope()

        var nameState by rememberSaveable { mutableStateOf(null) }

        var nameId: Int? = null

        var typeOfGenderInput = remember {
            mutableStateOf(TypeOfGender.NOTHING_SELECTED)
        }

        var typeOfTitleInput = remember {
            mutableStateOf(TypeOfTitle.NOTHING_SELECTED)
        }

        //GenderTypeSelectorComponent().GenderTypeSelectorComposable(typeOfGenderInput)
        // TitleTypeSelectorComponent().TitleTypSelectorComposable(typeOfTitleInput)

        var firstNameInput by remember {
            mutableStateOf("")
        }

        var lastNameInput by remember {
            mutableStateOf("")
        }

        Spacer(
            modifier = Modifier
                .height(5.dp)
        )

        GenderTypeSelectorComponent().GenderTypeSelectorComposable(typeOfGenderInput)

        Spacer(
            modifier = Modifier
                .height(25.dp)
        )

        TitleCreationComponent().TitleCreationComposable(typeOfTitleInput)

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )


        OutlinedTextField(
            modifier = Modifier
                .background(Color.White)
                .width(300.dp),
            value = firstNameInput,
            onValueChange = { firstNameInput = it },
            label = { Text("Vorname") },
            singleLine = true
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .background(Color.White)
                .width(300.dp),
            value = lastNameInput,
            onValueChange = { lastNameInput = it },
            label = { Text("Nachname") },
            singleLine = true
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        fun getName(): Name {
            var n: Name = Name(
                id = nameId,
                typeOfGender = typeOfGenderInput.value,
                typOfTitle = typeOfTitleInput.value,
                firstName = firstNameInput,
                lastName = lastNameInput
            );

            firstNameInput = ""; lastNameInput = ""
            return n
        }

/*

        Column(
            modifier = Modifier


               // .fillMaxSize()
                .height(200.dp)
                .padding(1.dp)
                .background(Color.Red)
            //.verticalScroll(rememberScrollState()).fillMaxSize()
            ,

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )

        {

            Column(
                modifier = Modifier

                    .width(200.dp)
                // .verticalScroll(rememberScrollState())

                ,
                //.verticalScroll(state = scrollState)
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
/*
                Row (modifier = Modifier
                    .width(300.dp)
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
                    Button(onClick = {onNavigateAddress()},

                        colors = ButtonDefaults.buttonColors
                            (contentColor = Color.White,   //pre-created colour
                            containerColor = AppBlue
                        ),

                        modifier = Modifier
                            .size(120.dp, 40.dp)){

                        Text(text = "Zu Address", fontSize = 12.sp)
                    }
                }*/
                /*
                                Row (modifier = Modifier
                                    .width(300.dp)
                                    .align((Alignment.Start))
                                ){
                                    Text(text = "Name",  fontWeight = FontWeight.Medium)
                                }*/




            }
        }*/

    }


}