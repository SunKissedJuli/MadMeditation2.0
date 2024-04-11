package com.coolgirl.madmeditation.Models

data class UserLoginData (
    val email:String,
    val password:String
)

data class UserLoginDataResponse (
    var id:String?,
    var email:String?,
    var nickName:String?,
    var avatar:String?,
    val token:String?,
)
