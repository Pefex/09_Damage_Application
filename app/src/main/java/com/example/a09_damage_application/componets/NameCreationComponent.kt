package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.a09_damage_application.data.entities.Name
import com.example.a09_damage_application.data.enums.TypeOfGender
import com.example.a09_damage_application.data.enums.TypeOfTitle

class NameCreationComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun NameCreationComposable(
        onNameChanged: (Name?) -> Unit
    ) {

        val coroutineScope = rememberCoroutineScope()

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
            return Name(
                id = nameId,
                typeOfGender = typeOfGenderInput.value,
                typOfTitle = typeOfTitleInput.value,
                firstName = firstNameInput,
                lastName = lastNameInput
            )
        }

        onNameChanged(getName())
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