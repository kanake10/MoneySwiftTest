package com.example.moneyswifttest

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moneyswifttest.views.ProductDetailScreen
import com.example.moneyswifttest.views.ProductsScreen
import com.example.moneyswifttest.views.Screen


@Composable
fun MainNav(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductsScreen.route
    ){
        composable(
            route = Screen.ProductsScreen.route
        ) {
            ProductsScreen(navController)
        }
        composable(
            route = Screen.ProductDetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) {
            val id = it.arguments?.getInt("id")
            ProductDetailScreen(navController, id!!)
        }
    }
}