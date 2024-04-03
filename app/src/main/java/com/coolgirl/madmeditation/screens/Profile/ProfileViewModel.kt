package com.coolgirl.madmeditation.screens.Profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class ProfileViewModel : ViewModel() {

    //он научится принимать аргумент фото
    fun fromProfiletoPhoto(navController: NavController){ navController.navigate("PHOTO") }
}