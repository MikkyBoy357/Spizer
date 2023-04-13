package com.mikkyboy.spizer.presentation.nav_bar_screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.mikkyboy.spizer.presentation.Greeting
import com.mikkyboy.spizer.presentation.Screen

@Composable
fun MicScreen(greetingName: String, navController: NavController) {
    var counter by remember {
        mutableStateOf(0)
    }

    fun decrement() {
        counter--
        println("Decrement $counter")
    }

    fun increment() {
        counter++
        println("Increment $counter")
    }

    Column {
        Greeting(greetingName = greetingName)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                onClick = {
                    decrement()
                },
            ) {
                Text(
                    text = "-", style = TextStyle(
                        fontSize = 20.sp
                    )
                )
            }
            Text(
                text = "$counter", style = TextStyle(
                    fontSize = 20.sp
                )
            )
            Button(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                onClick = {
                    increment()
                },
            ) {
                Text(
                    text = "+", style = TextStyle(
                        fontSize = 20.sp,
                    )
                )
            }
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .height(50.dp), onClick = {
            println("Next")
            navController.navigate(Screen.DetailScreen.withArgs(counter.toString()))
        }) {
            Text(text = "Next")
        }
    }
}
