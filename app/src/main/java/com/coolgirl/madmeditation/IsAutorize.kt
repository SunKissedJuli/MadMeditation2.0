package com.coolgirl.madmeditation

import android.util.Log
import androidx.compose.runtime.*
import androidx.datastore.preferences.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coolgirl.madmeditation.Models.ApiClient
import com.coolgirl.madmeditation.Models.ApiController
import com.coolgirl.madmeditation.Models.UserLoginData
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
var change by mutableStateOf("")
@Composable
fun IsAutorize() {
    InitDataStore()
    val userDataStore = GetDataStore()
    var isAutorize by remember { mutableStateOf(false) }
    coroutineScope?.launch {
        userDataStore?.data?.collect { pref: Preferences ->
            isAutorize = pref[UserScheme.IS_AUTORIZE] == true
        }
    }
    if (isAutorize) {
        var userLogin by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        coroutineScope?.launch {
            userDataStore?.data?.collect { pref: Preferences ->
                userLogin = pref[UserScheme.EMAIL]!!
                userPassword = pref[UserScheme.PASSWORD]!!
            }
        }
        LoadUserData(userLogin, userPassword)
       key(change){
           AppNavHost(navController = rememberNavController(), startDestination = Screen.Main.route)
       }
    } else {
        AppNavHost(navController = rememberNavController(), startDestination = Screen.OnBoarding.route)
    }
}


fun LoadUserData(userLogin : String, userPassword : String) {
    var apiClient = ApiClient.start().create(ApiController::class.java)
    val call: Call<UserLoginDataResponse>? = apiClient.autorizeClient(UserLoginData(userLogin, userPassword))
    call!!.enqueue(object : Callback<UserLoginDataResponse?> {
        override fun onResponse(call: Call<UserLoginDataResponse?>, response: Response<UserLoginDataResponse?>) {
            val responseUser = response.body()
            if (responseUser != null) {
                SetLocalUser(responseUser)
                change = "change"

            }
        }
        override fun onFailure(call: Call<UserLoginDataResponse?>, t: Throwable) {}
    })
}


