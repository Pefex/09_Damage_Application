package com.example.a09_damage_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.a09_damage_application.components.DamageCreationComponent
import com.example.a09_damage_application.data.DamageDatabase
import com.example.a09_damage_application.ui.theme._09_Damage_ApplicationTheme

class MainActivity : ComponentActivity() {

    /*object Type_Of_Demage {
        const val visualDefect = "Optischer Mangel"
        const val dangerToTheBuildingFabric = "Gefahr für Bausubstanz"
        const val dangerInDelay = "Gefahr in Verzug"
    }*/

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            DamageDatabase::class.java,
            "damages.db"
        ).build()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _09_Damage_ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Mainscreen(db)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Mainscreen(db: DamageDatabase) {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding((10.dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Radio_Buttons()
        DamageCreationComponent().DamageCreationComposable(db.dao)
    }
}

@Composable
fun Radio_Buttons(){

}



/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _09_Damage_ApplicationTheme {
        Greeting("Android")
    }
}*/