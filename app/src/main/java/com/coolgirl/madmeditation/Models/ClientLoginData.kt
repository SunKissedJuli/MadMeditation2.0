package com.coolgirl.madmeditation.Models

data class UserLoginData (
    val email:String,
    val password:String
)

data class UserLoginDataResponse (
    val id:String?,
    val email:String?,
    val nickName:String?,
    val avatar:String?,
    val token:String?,
)
