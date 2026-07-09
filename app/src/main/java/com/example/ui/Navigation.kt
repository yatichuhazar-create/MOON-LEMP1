package com.example.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.NotesScreen
import com.example.ui.screens.PlannerScreen
import com.example.ui.screens.SettingsScreen

sealed class Route(val route: String) {
    object Home : Route("home")
    object Notes : Route("notes")
    object Planner : Route("planner")
    object Settings : Route("settings")
}

@Composable
fun MoonlempNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(Route.Home.route) { HomeScreen(navController) }
        composable(Route.Notes.route) { NotesScreen(navController) }
        composable(Route.Planner.route) { PlannerScreen(navController) }
        composable(Route.Settings.route) { SettingsScreen(navController) }
    }
}
