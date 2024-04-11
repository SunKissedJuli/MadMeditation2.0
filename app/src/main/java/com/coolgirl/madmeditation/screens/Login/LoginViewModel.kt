package com.coolgirl.madmeditation.screens.Login

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.coolgirl.madmeditation.*
import com.coolgirl.madmeditation.Models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    var userLogin by mutableStateOf("")
        private set
    var userPassword by mutableStateOf("")
        private set

    fun updateUserPassword(password: String) {
        userPassword = password
    }
    fun updateUserLogin(login : String) {
        userLogin= login
    }

    fun LoadUserData(){
        val user = GetLocalUser()
        if(user?.nickName!=null && !user.nickName.equals("")){
            userLogin = user.email!!
        }
    }

    fun Autorization(navController: NavHostController) {
        var apiClient = ApiClient.start().create(ApiController::class.java)
        val call: Call<UserLoginDataResponse>? = apiClient.autorizeClient(UserLoginData(userLogin, userPassword))
        call!!.enqueue(object : Callback<UserLoginDataResponse?> {
            override fun onResponse(call: Call<UserLoginDataResponse?>, response: Response<UserLoginDataResponse?>) {
                val responseUser = response.body()
                if (responseUser != null) {
                    SetLocalUser(responseUser)
                    SetLoginData(UserLoginData(userLogin,userPassword))
                    navController.navigate(Screen.Main.route)
                }
            }
            override fun onFailure(call: Call<UserLoginDataResponse?>, t: Throwable) {}
        })
    }
}
