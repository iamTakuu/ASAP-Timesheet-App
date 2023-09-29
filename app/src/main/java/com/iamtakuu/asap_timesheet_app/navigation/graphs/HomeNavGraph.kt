package com.iamtakuu.asap_timesheet_app.navigation.graphs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamtakuu.asap_timesheet_app.screens.BottomBarScreen
import com.iamtakuu.asap_timesheet_app.screens.StatisticsScreen
import com.iamtakuu.asap_timesheet_app.screens.TaskCreationScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Text(text = "Home")
        }
        composable(route = BottomBarScreen.Stats.route) {
            StatisticsScreen()
        }
        composable(route = BottomBarScreen.Categories.route) {
            Text(text = "Categories")
        }
        composable(route = BottomBarScreen.Task.route) {
            TaskCreationScreen()
        }

    }
}
//        composable(route = BottomBarScreen.Categories.route) {
//            ScreenContent(
//                name = BottomBarScreen.Categories.route,
//                onClick = { }
//            )