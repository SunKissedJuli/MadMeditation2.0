package com.coolgirl.madmeditation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun IsAutorize(){
    val isAutorize = pref.getBoolean("isAutorize", false)
    val isFirstTap = pref.getBoolean("isFirstTap", true)
    if(isAutorize){
        GetUser()
        AppNavHost(navController = rememberNavController(), startDestination = "MAIN")
    }else if(isFirstTap){
        pref.edit().putBoolean("isFirstTap", false).apply()
        AppNavHost(navController = rememberNavController(), startDestination = "ONBOARDING")
    }
}