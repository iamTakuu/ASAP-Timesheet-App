package com.iamtakuu.asap_timesheet_app.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Task : BottomBarScreen(
        route = "TASK",
        title = "TASK",
        icon = Icons.Default.WatchLater
    )

    object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object Stats : BottomBarScreen(
        route = "STATS",
        title = "STATS",
        icon = Icons.Default.GraphicEq
    )

    object Categories : BottomBarScreen(
        route = "CATEGORIES",
        title = "CATEGORIES",
        icon = Icons.Default.Schema
    )
}