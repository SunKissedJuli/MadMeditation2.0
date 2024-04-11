package com.coolgirl.madmeditation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = this.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        setContent {
            IsAutorize()
        }
    }




}



