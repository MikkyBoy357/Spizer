package com.mikkyboy.spizer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.mikkyboy.spizer.presentation.nav_bar_screens.CartScreen
import com.mikkyboy.spizer.presentation.nav_bar_screens.MicScreen
import com.mikkyboy.spizer.presentation.nav_bar_screens.SettingScreen
import com.mikkyboy.spizer.presentation.theme.PaytalkGreen
import com.mikkyboy.spizer.presentation.widgets.GridItem
import com.mikkyboy.spizer.presentation.widgets.NavBarItem

@Composable
fun HomeScreen(navController: NavController, greetingName: String) {


    val selectedItem = remember {
        mutableStateOf(2)
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

    val pageViews =
        listOf(
            Text(text = "Settings"),
            MicScreen(greetingName = greetingName, navController = navController),
            Text(text = "Cart"),
        )

    val numbers = (0..20).toList()

    Scaffold(
        modifier = Modifier.background(Color.Black),
        positionIndicator = { PositionIndicator(scalingLazyListState = scalingLazyState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            // Top Navigation Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 10.dp, start = 50.dp, end = 50.dp)
//                    .background(Color.Blue)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavBarItem(
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
                NavBarItem(
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
                NavBarItem(
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
            // PageView ==> Something Like Fragments
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
                    when (selectedItem.value) {
                        0 -> SettingScreen()
                        1 -> MicScreen(
                            greetingName = greetingName,
                            navController = navController
                        )
                        else -> CartScreen()
                    }
                }
            }
        }
    }
}
