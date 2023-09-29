package com.iamtakuu.asap_timesheet_app.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamtakuu.asap_timesheet_app.screens.LoginScreen
import com.iamtakuu.asap_timesheet_app.screens.SignUpScreen


public fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onSignUpClick = {
                   // navController.popBackStack() // because nested nav graph
                    navController.navigate(AuthScreen.SignUp.route)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(
                onSignUpClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}