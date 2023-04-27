package com.mikkyboy.spizer.presentation.nav_bar_screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikkyboy.spizer.presentation.widgets.GridItem

@Composable
fun CartScreen() {
    Column {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GridItem(
                modifier = Modifier
                    .fillMaxWidth(0.49f),
                itemPosition = 0,
            )
            Spacer(modifier = Modifier.width(8.dp))
            GridItem(
                modifier = Modifier
                    .fillMaxWidth(0.99f),
                itemPosition = 1,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GridItem(
                modifier = Modifier
                    .fillMaxWidth(0.49f),
                itemPosition = 2,
            )
            Spacer(modifier = Modifier.width(8.dp))
            GridItem(
                modifier = Modifier
                    .fillMaxWidth(0.99f),
                itemPosition = 3,
            )
        }
    }
}