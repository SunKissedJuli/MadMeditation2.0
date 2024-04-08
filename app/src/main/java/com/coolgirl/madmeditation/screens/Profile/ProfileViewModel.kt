package com.coolgirl.madmeditation.screens.Profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.GetLocalUser
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import com.coolgirl.madmeditation.R
import java.io.File
import java.util.*
import kotlin.random.Random


class ProfileViewModel : ViewModel() {

    private var user: UserLoginDataResponse? = SetUser()
    var imagee = ""
    private var imageList: List<Any> = SetImageList()



    fun GetUser(): UserLoginDataResponse? {
        return user
    }

    fun SetUser(): UserLoginDataResponse? {
        val user = GetLocalUser()
        return user
    }
    fun SetImageList(): List<Any> {
        if(!imagee.equals("")){
            var imageList = listOf<Any>(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                imagee
            )
            return imageList
        }else{
            var imageList = listOf<Any>(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
            )
            return imageList
        }
    }

    fun fromProfiletoPhoto(navController: NavController, i: Int) {
        val selectedPhoto = imageList.get(i)
        navController.navigate("PHOTO/${selectedPhoto}")
    }

    @Composable
    fun NewImage() : ManagedActivityResultLauncher<String, Uri?> {
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> imageUri = uri }
        if(imageUri!=null&& imageUri.toString()!=(imagee)){
            imagee = imageUri.toString()
        }
        return launcher
    }
}



