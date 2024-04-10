package com.coolgirl.madmeditation

import android.util.Log
import com.coolgirl.madmeditation.screens.Profile.ImageDataDeserializer
import com.coolgirl.madmeditation.screens.Profile.ProfileState
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class ImageLoader() {

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

    fun RemovePhoto(imageId : Int){
        val json = pref.getString("key", null)
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(ProfileState::class.java, ImageDataDeserializer())
        val gson = gsonBuilder.create()
        if (json != null) {
            val listType = object : TypeToken<MutableList<ProfileState>>() {}.type
            val photoList = gson.fromJson<MutableList<ProfileState>>(json, listType)
            if (imageId >= 0 && imageId < photoList.size) {
                photoList.removeAt(imageId)
                val newJson = gson.toJson(photoList)
                val editor = pref.edit()
                editor.putString("key", newJson)
                editor.apply()
            }
        }
    }
}