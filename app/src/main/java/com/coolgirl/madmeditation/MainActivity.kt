package com.coolgirl.madmeditation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.coolgirl.madmeditation.Models.UserLoginDataResponse

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = this.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        setContent {
            IsAutorize()
        /*   androidx.compose.material.Surface(
               modifier = Modifier.fillMaxSize()) {
               AppNavHost(navController = rememberNavController())
           }*/
        }
    }




}



