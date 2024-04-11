package com.coolgirl.madmeditation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun IsAutorize(){
    val isAutorize = pref.getBoolean("isAutorize", false)
    if(isAutorize){
        LoadLocalUser()
        AppNavHost(navController = rememberNavController(), startDestination = Screen.Main.route)
    }else {
        AppNavHost(navController = rememberNavController(), startDestination = Screen.OnBoarding.route)
    }
}
