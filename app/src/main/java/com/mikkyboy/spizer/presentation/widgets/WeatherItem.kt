package com.mikkyboy.spizer.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.mikkyboy.spizer.R
import com.mikkyboy.spizer.presentation.Constants
import com.mikkyboy.spizer.presentation.theme.PaytalkGreen

@Composable
fun WeatherItem(modifier: Modifier, title: String?, value: String?, icon: Int) {
    val iconModifier = Modifier.size(18.dp)

    Column(
        modifier = modifier
            .width(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
            .background(
                Color.Gray.copy(
                    alpha = 0.4f
                )
            )
            .padding(vertical = 5.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Mic",
            tint = PaytalkGreen,
            modifier = iconModifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "$title",
            style = TextStyle(fontSize = 8.sp),
        )
        Text(
            text = "$value",
            style = TextStyle(fontSize = 7.sp),
        )
    }

}