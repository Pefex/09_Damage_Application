package com.example.a09_damage_application.componets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.a09_damage_application.data.enums.TypeOfGender
import com.example.a09_damage_application.data.enums.TypeOfTitle
import com.example.a09_damage_application.data.interfaces.NameDao

class NameCreationComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun NameCreationComposable(dao: NameDao){

        val coroutineScope = rememberCoroutineScope()

        var typeOfGenderInput = remember{
            mutableStateOf(TypeOfGender.NOTHING_SELECTED)
        }

        var typeOfTitleInput = remember{
            mutableStateOf(TypeOfTitle.NOTHING_SELECTED)
        }

        // GenderTypeSelectorComponent().GenderTypSelectorComposable(typeOfGenderInput)
        // TitleTypeSelectorComponent().TitleTypSelectorComposable(typeOfTitleInput)

        var descrptionFirstName by remember{
            mutableStateOf("")
        }

        var descriptionLastName by remember{
            mutableStateOf("")
        }



    }
}