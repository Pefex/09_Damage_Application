package com.example.a09_damage_application.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a09_damage_application.R
import com.example.a09_damage_application.ui.theme.AppBlue

class DamageListCreationComponent{

    @ExperimentalMaterial3Api
    @Composable
    fun DamageListCreationComposable(onNavigateName: () -> Unit, onNavigateDamage: () -> Unit,onNavigateAddress: () -> Unit ) {

        //var id1:String = "p1.png"
        //var id2 = 2
        //val recipeImage: Bitmap = getImageFromAssets(p001.png)

        val imgRes = painterResource(id = R.drawable.p1)

        val imageId = arrayOf(
            R.drawable.p1,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
        )

        val names = arrayOf(
            "Peperonie",
            "Vegan",
            "Mexican"
        )

        val ingrdients = arrayOf(
            "Tomato,, cheese, oregano",
            "Tomato, cheese, green paprika",
            "Tomato, "
        )

       //modifier: Modifier = Modifier
        //imageId: Array<Int>;
        //names: Array<String>


        Column {


        }
        Spacer(modifier = Modifier
            .height(30.dp)
            .width(150.dp)
        )


    Column (modifier = Modifier
    .verticalScroll(rememberScrollState())){

        Row (modifier = Modifier
            .fillMaxWidth()
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
            Button(onClick = {onNavigateName()},

                colors = ButtonDefaults.buttonColors
                    (contentColor = Color.White,   //pre-created colour
                    containerColor = AppBlue
                ),

                modifier = Modifier
                    .size(120.dp, 40.dp)){

                Text(text = "Zu Name", fontSize = 12.sp)
            }

        }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Magenta)){


    }





    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Gray)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Green)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Yellow)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Blue)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Red)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Cyan)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Magenta)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .background(Color.Gray)){
    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        ){
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "pic2")

    }
    Box (modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        )
   {
       Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "pic1")
    }

}    }



}