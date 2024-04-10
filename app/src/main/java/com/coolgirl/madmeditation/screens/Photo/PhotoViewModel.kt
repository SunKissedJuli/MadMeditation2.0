package com.coolgirl.madmeditation.screens.Photo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.ImageLoader
import com.coolgirl.madmeditation.screens.Profile.ProfileState

class PhotoViewModel : ViewModel() {
    val imageLoader = ImageLoader()
    private val imageList = imageLoader.LoadPhotos()

    fun GetImage(imageId: Int) : ProfileState{
        return imageList.get(imageId)
    }

    fun RemoveImage(imageId: Int, navController: NavController){
        imageLoader.RemovePhoto(imageId)
        navController.navigate("PROFILE")
    }
}