package com.example.a09_damage_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.a09_damage_application.componets.DamageCreationComponent
import com.example.a09_damage_application.componets.DamageListCreationComponent
import com.example.a09_damage_application.componets.OwnerCreationComponent
import com.example.a09_damage_application.componets.OwnerListCreationComponent
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
fun Mainscreen(db: AppDatabase) {
    //AddressCreationComponent().AddressCreationComposable(db.addressDao)
    //OwnerCreationComponent().OwnerCreationComposable


    //NameCreationComponent().NameCreationComposable(db.nameDao)

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "damageCreation") {

        composable("damageCreation") {
            DamageCreationComponent().DamageCreationComposable(
                db.damageDao,
                //onNavigateDamageList = { navController.navigate("damageListCreation") },
                onNavigateOwner = { navController.navigate("ownerCreation") }
            )
        }

        composable("ownerCreation") {
            OwnerCreationComponent().OwnerCreationComposable(
                db.ownerDao,
                db.contactWithAddressDao,
                db.addressDao,
                db.contactDao,

                onNavigateDamage = { navController.navigate("damageCreation") },
                onNavigateOwnerList = { navController.navigate("ownerListCreation")}
            //onNavigateDamageList = { navController.navigate("damageListCreation") }
            )
        }

        composable("ownerListCreation"){
            OwnerListCreationComponent().OwnerListComposable(onNavigateDamage = { /*TODO*/ }) {
                
            }

        }
/*
        composable("damageListCreation") {
            DamageListCreationComponent().DamageListCreationComposable(
                onNavigateDamage = { navController.navigate("damageCreation") },
                onNavigateOwner = { navController.navigate("nameCreation") },
                )
        }*/



        /*

        composable("addressCreation") {
            AddressCreationComponent().AddressCreationComposable(
                db.addressDao,
                onNavigateDamage = { navController.navigate("damageCreation") },
                onNavigateName = { navController.navigate("nameCreation") })
        }




        composable("contactCreation") {
            ContactCreationComponent().ContactCreationComposable(
                db.contactDao,

                onNavigateDamage = { navController.navigate("damageCreation") },
                onNavigateName = { navController.navigate("nameCreation") }
            )
        }

        composable("contactCreation") {
            AddAndEditOwnersComponent().AddAndEditOwnersComposable(
                db.addAndEditOwnerDao,

                onNavigateDamage = { navController.navigate("damageCreation") },
                onNavigateAddress = { navController.navigate("addressCreation") }
            )
        }*/


        //composable("addressCreation"){ NameCreationComponent().NameCreationComposable(db.nameDao,  onNavigateDamage = {navController.navigate("damageCreation")})}
        //composable("addressCreation"){ NameCreationComponent().NameCreationComposable(db.nameDao,  onNavigateAddress = {navController.navigate("addressCreation")})}
    }
}
/*
    Column (modifier = Modifier
        .fillMaxSize()
        .padding((10.dp))
        .background(color = Color(251, 249, 241)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Radio_Buttons()
        //DamageCreationComponent().DamageCreationComposable(db.damageDao)
        //AddressCreationComponent().AddressCreationComposable(db.addressDao)
        //NameCreationComponent().NameCreationComposable(db.nameDao)
    }*/

/*
@Composable
fun Radio_Buttons() {

}*/



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