package com.iamtakuu.asap_timesheet_app.navigation.graphs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamtakuu.asap_timesheet_app.screens.BottomBarScreen
import com.iamtakuu.asap_timesheet_app.screens.CategoriesScreen
import com.iamtakuu.asap_timesheet_app.screens.StatsAchievements
import com.iamtakuu.asap_timesheet_app.screens.TaskCardSimple
import com.iamtakuu.asap_timesheet_app.screens.TaskCreationScreen
import com.iamtakuu.asap_timesheet_app.screens.task3
import com.iamtakuu.asap_timesheet_app.screens.task4
import com.iamtakuu.asap_timesheet_app.screens.task5

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Column (Modifier
                .padding(8.dp)) {
                TaskCardSimple(task = task3, isFavorite = true){}
                TaskCardSimple(task = task4, isFavorite = false){}
                TaskCardSimple(task = task5, isFavorite = false){}
            }
        }
        composable(route = BottomBarScreen.Stats.route) {
            StatsAchievements()
        }
        composable(route = BottomBarScreen.Categories.route) {
            CategoriesScreen()
        }
        composable(route = BottomBarScreen.Task.route) {
            TaskCreationScreen()
        }

    }
}