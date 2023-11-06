package com.example.a09_damage_application.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class BoxRounded {
    var boxRounded: Modifier = Modifier
        .fillMaxWidth()
        // .height(300.dp)
        .clip(shape = RoundedCornerShape(5.dp))
        .padding(1.5.dp)
        .clip(RoundedCornerShape(1.5.dp))
        .background(Color.White, shape = RoundedCornerShape(5.dp))
        //.border(BorderStroke(0.dp, SolidColor(Color.Red)))
        .padding(8.dp)
}