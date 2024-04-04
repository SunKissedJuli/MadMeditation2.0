package com.coolgirl.madmeditation.screens.Profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.GetLocalUser
import com.coolgirl.madmeditation.Models.Feelings
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import com.coolgirl.madmeditation.R

class ProfileViewModel : ViewModel() {

    private var user : UserLoginDataResponse? = SetUser()

    fun GetUser() : UserLoginDataResponse?{
        return user
    }

    fun SetUser() : UserLoginDataResponse?{
        val user = GetLocalUser()
        return user
    }

    fun SetImageList() : List<Int>{
        var imageList = listOf<Int>(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
        )
        return imageList
    }

    //он научится принимать аргумент фото
    fun fromProfiletoPhoto(navController: NavController){ navController.navigate("PHOTO") }
}