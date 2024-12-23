package com.example.moviesapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesapp.presentation.ui.popular.PopularPeopleScreen
import com.example.moviesapp.presentation.ui.search.SearchPeopleScreen
import com.example.moviesapp.presentation.ui.details.PersonDetailsScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "popular") {
        // Popular People Screen
        composable("popular") {
            PopularPeopleScreen()
        }

        // Search People Screen
        composable("search") {
            SearchPeopleScreen()
        }

        // Person Details Screen
        composable("details/{personId}") { backStackEntry ->
            val personId = backStackEntry.arguments?.getString("personId")?.toInt() ?: 0
            PersonDetailsScreen(personId)
        }
    }
}
