package com.coolgirl.madmeditation

import com.coolgirl.madmeditation.Models.UserLoginDataResponse

private var user : UserLoginDataResponse? = null

fun SetLocalUser(newUser : UserLoginDataResponse){
    user = UserLoginDataResponse(
        newUser.id,
        newUser.email,
        newUser.nickName,
        newUser.avatar,
        newUser.token)
}

fun GetLocalUser() : UserLoginDataResponse?{
    return user
}


