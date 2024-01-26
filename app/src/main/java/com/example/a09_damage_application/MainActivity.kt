package com.example.a09_damage_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.a09_damage_application.componets.AddressCreationComponent
import com.example.a09_damage_application.componets.NameCreationComponent
import com.example.a09_damage_application.data.AppDatabase
import com.example.a09_damage_application.ui.theme._09_Damage_ApplicationTheme

class MainActivity : ComponentActivity() {
    private val appDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase :: class.java,
            "address.db"
        ).build()
    }

    /*object Type_Of_Demage {
        const val visualDefect = "Optischer Mangel"
        const val dangerToTheBuildingFabric = "Gefahr für Bausubstanz"
        const val dangerInDelay = "Gefahr in Verzug"
    }*/

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
                    //Mainscreen(damageDatabase)
                    Mainscreen(appDatabase)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Mainscreen(db: AppDatabase){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding((10.dp))
        .background(color = Color(251, 249, 241)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Radio_Buttons()
        //DamageCreationComponent().DamageCreationComposable(db.damageDao)
        AddressCreationComponent().AddressCreationComposable(db.addressDao)
        NameCreationComponent().NameCreationComposable(db.nameDao)
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