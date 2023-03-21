package com.mikkyboy.spizer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.*
import com.mikkyboy.spizer.R
import com.mikkyboy.spizer.presentation.theme.PaytalkGreen

@Composable
fun HomeScreen(navController: NavController, greetingName: String) {
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

    val scalingLazyListState = rememberScalingLazyListState()

    val selectedItem = remember {
        mutableStateOf(1)
    }

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 0,
//            initialCenterItemScrollOffset = 80
            initialCenterItemScrollOffset = 0
        )
    }
    val focusRequester = remember { FocusRequester() }
    val iconModifier = Modifier.size(18.dp)
    val shape = RoundedCornerShape(30.dp)

    Scaffold(
        modifier = Modifier.background(Color.Black),
        positionIndicator = { PositionIndicator(scalingLazyListState = scalingLazyState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 10.dp, start = 60.dp, end = 60.dp)
//                    .background(Color.Blue)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BarItem(
                    onClick = {
                        selectedItem.value = 0
                        println("selectedItem => ${selectedItem.value}")
                    },
                    selectedItem = selectedItem.value,
                    itemIndex = 0,
                    iconModifier = iconModifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_settings_24),
                        contentDescription = "Settings",
                        tint = PaytalkGreen,
                        modifier = iconModifier

                    )
                }
                BarItem(
                    onClick = {
                        selectedItem.value = 1
                        println("selectedItem => ${selectedItem.value}")
                    },
                    selectedItem = selectedItem.value,
                    itemIndex = 1,
                    iconModifier = iconModifier
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_mic_none_24),
                        contentDescription = "Mic",
                        tint = PaytalkGreen,
                        modifier = iconModifier

                    )
                }
                BarItem(
                    onClick = {
                        selectedItem.value = 2
                        println("selectedItem => ${selectedItem.value}")
                    },
                    selectedItem = selectedItem.value,
                    itemIndex = 2,
                    iconModifier = iconModifier
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
                        contentDescription = "Cart",
                        tint = PaytalkGreen,
                        modifier = iconModifier
                    )
                }
            }
            ScalingLazyColumn(
//                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 40.dp
                ),
                autoCentering = false,
                anchorType = ScalingLazyListAnchorType.ItemStart,
                verticalArrangement = Arrangement.Center,
                state = scalingLazyState
            ) {
                item {
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
            }
        }
    }
}

@Composable
fun BarItem(
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
            .clickable { onClick() }
            .clip(RoundedCornerShape(10.dp))
            .background(if (selectedItem == itemIndex) Color.Gray.copy(alpha = 0.4f) else Color.Transparent),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}