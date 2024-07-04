package com.example.a09_damage_application.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.data.interfaces.BuildingElementDao
import com.example.a09_damage_application.data.interfaces.LocalizationDao
import com.example.a09_damage_application.viewmodels.LocalizationViewModel

class StructureElementAndBuildingElementListComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun StructureElementAndBuildingElementListComposable(
        daoLocalization: LocalizationDao,  // "dao ist die lokale Variable für das "LocalizationDao"
        daoBuildingElement: BuildingElementDao,
        rootLocalizationId: Int? = 0,  // Warum notwendig? Wird momentan nicht benutzt
        localizationViewModel: LocalizationViewModel,
        onNavigateDamage: () ->Unit,
        onNavigateLocalizationList: ()->Unit
    ){

       // val localizations by localizationViewModel.localizations.collectAsState()

        Column (modifier = Modifier
            .fillMaxSize()
            .heightIn(100.dp)
            .background(Color.Yellow)
            .padding(2.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            //Texteingabe für Titel

            Row (modifier = Modifier
                .width(400.dp)
                .height(50.dp)
            ){

                Button(onClick = {
                    onNavigateDamage()}) {
                    Text(text = "Zu Schäden", fontSize = 14.sp)
                }
                Button(onClick = {onNavigateLocalizationList()}) {
                    Text(text = "Zur Schadensverortung", fontSize = 12.sp)

                }
            }

            Column {
                LazyColumn {
                    items(localizations){ localization ->
                        Text(text = "LOCALIZAZION: ${localization.name}, LocalizationID:  ${localization.localizationId}")

                    }
                }

            }



/*
       StructureListComponent().StructureListComposable(
            daoLocalization = daoLocalization,
            daoBuildingElement = daoBuildingElement,
            localizationId = 0,

           )*/

    }


}}