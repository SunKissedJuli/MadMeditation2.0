package com.coolgirl.madmeditation

import android.content.Context
import android.content.SharedPreferences
import com.coolgirl.madmeditation.Models.UserLoginDataResponse

var user : UserLoginDataResponse? = null
lateinit var pref : SharedPreferences
fun SetUser(newUser : UserLoginDataResponse){
    user = UserLoginDataResponse(
        newUser.id,
        newUser.email,
        newUser.nickName,
        newUser.avatar,
        newUser.token
    )
    val editor = pref.edit()
    editor.putBoolean("isAutorize", true)
    editor.putString("id", user?.email)
    editor.putString("email", user?.email)
    editor.putString("nickName", user?.nickName)
    editor.putString("avatar", user?.avatar)
    editor.putString("token", user?.avatar)
    editor.apply()
}
fun GetUser(){
    user = UserLoginDataResponse(
        pref.getString("id", ""),
        pref.getString("email", ""),
        pref.getString("nickName", ""),
        pref.getString("avatar", ""),
        pref.getString("token", ""),
    )
}

