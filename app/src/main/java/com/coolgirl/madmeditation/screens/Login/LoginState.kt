package com.coolgirl.madmeditation.screens.Login

data class LoginState(
    val login : String,
    val password : String
) {
    companion object{
        val InitState = LoginState(
            login ="",
            password = ""
        )
    }

}