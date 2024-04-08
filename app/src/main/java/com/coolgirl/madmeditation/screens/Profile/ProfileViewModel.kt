package com.coolgirl.madmeditation.screens.Profile

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.GetLocalUser
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.pref
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ProfileViewModel : ViewModel() {

    private var user: UserLoginDataResponse? = SetUser()
    var imagee = ""
    private val imageList = LoadPhotos()

    fun GetUser(): UserLoginDataResponse? {
        return user
    }

    fun SetUser(): UserLoginDataResponse? {
        val user = GetLocalUser()
        return user
    }
    fun SetImageList(): List<Any> {
        return imageList
    }

    fun fromProfiletoPhoto(navController: NavController, i: Int) {
        val selectedPhoto = imageList.get(i).toString()
        val photoPath = selectedPhoto.substring(((selectedPhoto.indexOf("=") + 1)), (selectedPhoto.lastIndexOf(")")))
        navController.navigate("PHOTO/${photoPath.toString()}")
    }

    @Composable
    fun NewImage() : ManagedActivityResultLauncher<String, Uri?> {
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> imageUri = uri }
        if(imageUri!=null&& imageUri.toString()!=(imagee)){
            imagee = imageUri.toString()
            imageList.add(ProfileState.ImageUri(imagee))
            SavePhotos(imageList)
        }
        return launcher
    }

    fun SavePhotos(photoList : List<ProfileState>) {
        val editor = pref.edit()
        val json = Gson().toJson(photoList)
        editor.putString("key", json)
        editor.apply()
    }

    fun LoadPhotos(): MutableList<ProfileState> {
        val json = pref.getString("key", null)
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(ProfileState::class.java, ImageDataDeserializer())
        val gson = gsonBuilder.create()
        if(json!=null){
            val listType = object : TypeToken<MutableList<ProfileState>>() {}.type
            return gson.fromJson(json, listType)
        }else{
            SavePhotos(listOf(
                ProfileState.ImageResource(R.drawable.image1),
                ProfileState.ImageResource(R.drawable.image2),
                ProfileState.ImageResource(R.drawable.image3),
                ProfileState.ImageResource(R.drawable.image4)))
                val json = pref.getString("key", null)
            val listType = object : TypeToken<MutableList<ProfileState>>() {}.type
            return gson.fromJson(json, listType)
        }
    }
    class ImageDataDeserializer : JsonDeserializer<ProfileState> {
        override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): ProfileState {
            val jsonObject = json.asJsonObject
            return if (jsonObject.has("resourceId")) {
                ProfileState.ImageResource(jsonObject.get("resourceId").asInt)
            } else {
                ProfileState.ImageUri(jsonObject.get("uri").asString)
            }
        }
    }
}



