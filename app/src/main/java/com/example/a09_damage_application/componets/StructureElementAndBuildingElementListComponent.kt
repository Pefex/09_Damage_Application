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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a09_damage_application.data.entities.Localization
import com.example.a09_damage_application.data.interfaces.BuildingElementDao
import com.example.a09_damage_application.data.interfaces.LocalizationDao

class StructureElementAndBuildingElementListComponent {

    @ExperimentalMaterial3Api
    @Composable

    fun StructureElementAndBuildingElementListComposable(
        daoLocalization: LocalizationDao,  // "dao ist die lokale Variable für das "LocalizationDao"
        daoBuildingElement: BuildingElementDao,
        rootLocalizationId: Int? = 0,  // Warum notwendig? Wird momentan nicht benutzt
        onNavigateDamage: () ->Unit,
        onNavigateLocalizationList: ()->Unit
    ){

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

       StructureListComponent().StructureListComposable(
            daoLocalization = daoLocalization,
            daoBuildingElement = daoBuildingElement,
            localizationId = 0,

           )

    }


}}