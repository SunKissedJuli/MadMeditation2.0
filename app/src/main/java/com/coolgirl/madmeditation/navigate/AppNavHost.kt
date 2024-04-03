package com.coolgirl.madmeditation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.coolgirl.madmeditation.screens.*
import com.coolgirl.madmeditation.screens.Login.LoginScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.OnBoarding.route
){
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavigationItem.OnBoarding.route){
            OnBoardingScreen(navController)
        }
        composable(NavigationItem.Login.route){
            LoginScreen(navController)
        }
        composable(NavigationItem.Register.route){
            Register(navController)
        }
        composable(NavigationItem.Photo.route){
            Photo(navController)
        }

        composable(NavigationItem.Main.route){
            MainScreen(navController)
        }
        composable(NavigationItem.Profile.route){
            ProfileScreen(navController)
        }

        composable(NavigationItem.Menu.route){
            Menu(navController)
        }
    }
}