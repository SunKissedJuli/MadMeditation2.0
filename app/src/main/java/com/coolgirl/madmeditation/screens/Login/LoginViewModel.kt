package com.coolgirl.madmeditation.screens.Login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.coolgirl.madmeditation.Models.ApiClient
import com.coolgirl.madmeditation.Models.ApiController
import com.coolgirl.madmeditation.Models.UserLoginData
import com.coolgirl.madmeditation.SetLocalUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun Autorization(navController: NavHostController) {

        var apiClient = ApiClient.start().create(ApiController::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usr = apiClient.autorizeClient(UserLoginData(userLogin, userPassword))
                kotlin.run {
                    if (usr.nickName!=null){
                        withContext(Dispatchers.Main) {
                            SetLocalUser(usr)
                            navController.navigate("MAIN")
                        }
                    }
                }
            }
            catch (e: Exception) {
                Log.e("tag", "В LoginFragment exeption : ${e.message}")
            }
        }
    }

}
