package com.coolgirl.madmeditation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.coolgirl.madmeditation.screens.*
import com.coolgirl.madmeditation.screens.Login.LoginScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination : String = Screen.OnBoarding.route
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Screen.OnBoarding.route){
            OnBoardingScreen(navController)
        }
        composable(Screen.Login.route){
            LoginScreen(navController)
        }
        composable(Screen.Register.route){
            Register(navController)
        }
        composable(Screen.Photo.route,
        arguments = listOf(navArgument("user_photo_id"){
            type = NavType.IntType
            defaultValue = 0
        })){
            val user_photo_id: Int = it.arguments?.getInt("user_photo_id")!!
            PhotoScreen(navController,user_photo_id)
        }

        composable(Screen.Main.route){
            MainScreen(navController)
        }
        composable(Screen.Profile.route){
            ProfileScreen(navController)
        }

        composable(Screen.Menu.route){
            Menu(navController)
        }
    }
}