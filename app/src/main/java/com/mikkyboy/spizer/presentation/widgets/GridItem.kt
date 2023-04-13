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
fun GridItem(modifier: Modifier, itemPosition: Int) {
    val iconModifier = Modifier.size(18.dp)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
            .background(
                Color.Gray.copy(
                    alpha = 0.4f
                )
            )
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .background(Color.Green.copy(alpha = 0.5f))
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_mic_none_24),
                contentDescription = "Mic",
                tint = PaytalkGreen,
                modifier = iconModifier.height(100.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = Constants().gridItemsList[itemPosition].label,
            style = TextStyle(fontSize = 16.sp),
        )
    }
}