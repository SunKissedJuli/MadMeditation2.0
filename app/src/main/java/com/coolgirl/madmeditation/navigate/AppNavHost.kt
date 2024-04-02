package com.coolgirl.madmeditation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.coolgirl.madmeditation.screens.*

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
            OnBoarding(navController)
        }
        composable(NavigationItem.Login.route){
            Login(navController)
        }
        composable(NavigationItem.Register.route){
            Register(navController)
        }
        composable(NavigationItem.Photo.route){
            Photo(navController)
        }
   /*     composable("MAIN?nickName={nickName}/{avatar}",
        arguments = listOf(
            navArgument("nickName"){
                type = NavType.StringType
                defaultValue = "ноунейм"
            },
            navArgument("avatar"){
                type = NavType.StringType
                defaultValue = "https://yt3.googleusercontent.com/ytc/APkrFKbEDM4mxJA_MdEvGjuZDwOMEEl7-TXrzdUqxdlO=s900-c-k-c0x00ffffff-no-rj"
            })
        ) { backStackEntry ->
            val nickName = backStackEntry.arguments?.getString("nickName")
            val avatar = backStackEntry.arguments?.getString("avatar")
            Main(navController, nickName, avatar)
        }

        composable("PROFILE?nickName={nickName}/{avatar}",
            arguments = listOf(
                navArgument("nickName"){
                    type = NavType.StringType
                    defaultValue = "ноунейм"
                },
                navArgument("avatar"){
                    type = NavType.StringType
                    defaultValue = "https://yt3.googleusercontent.com/ytc/APkrFKbEDM4mxJA_MdEvGjuZDwOMEEl7-TXrzdUqxdlO=s900-c-k-c0x00ffffff-no-rj"
                })
        ) { backStackEntry ->
            val nickName = backStackEntry.arguments?.getString("nickName")
            val avatar = backStackEntry.arguments?.getString("avatar")
            Profile(navController, nickName, avatar)
        }*/

        composable(NavigationItem.Main.route){
            Main(navController)
        }
        composable(NavigationItem.Profile.route){
            Profile(navController)
        }

        composable(NavigationItem.Menu.route){
            Menu(navController)
        }
    }
}