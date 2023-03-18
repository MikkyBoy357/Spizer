package com.mikkyboy.spizer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun DetailScreen(navController: NavController, counter: String?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = "Hi $counter"
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .height(50.dp), onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Back")
        }
    }
}