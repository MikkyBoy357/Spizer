package com.mikkyboy.spizer.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NavBarItem(
    onClick: () -> Unit,
    selectedItem: Int,
    itemIndex: Int,
    iconModifier: Modifier,
    content: @Composable() () -> Unit,
) {
    Row(
        modifier = Modifier
            .width(35.dp)
            .height(24.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .background(if (selectedItem == itemIndex) Color.Gray.copy(alpha = 0.4f) else Color.Transparent),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}