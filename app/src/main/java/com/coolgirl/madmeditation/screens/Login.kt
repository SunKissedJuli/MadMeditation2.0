package com.coolgirl.madmeditation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.coolgirl.madmeditation.Models.ApiClient
import com.coolgirl.madmeditation.Models.ApiController
import com.coolgirl.madmeditation.Models.UserLoginData
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.SetUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



fun Autorization(login: String, password: String, navController: NavHostController) {

    var apiClient = ApiClient.start().create(ApiController::class.java)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val usr = apiClient.autorizeClient(UserLoginData(login, password))
            kotlin.run {
                if (usr.nickName!=null){
                    withContext(Dispatchers.Main) {
                        SetUser(usr)
                        fromLogintoMain(usr, navController)
                    }
                }
            }
        }
        catch (e: Exception) {
            Log.e("tag", "В LoginFragment exeption : ${e.message}")
        }
    }
}

fun fromLogintoMain(user : UserLoginDataResponse, navController: NavHostController){

    navController.navigate("MAIN?nickName=${user.nickName}/${user.avatar}")
}

fun fromLogintoRegister(navController: NavHostController){
    navController.navigate("REGISTER")
}

@Composable
fun Login(navController: NavHostController){
    val loginField = remember { mutableStateOf("") }
    val passwordField = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_green)),
        verticalArrangement = Arrangement.Bottom

    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.leaves),
            contentDescription = "image"
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .padding(top = 40.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(0.3f),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "image"
            )
            Text(
                text = "Sign in",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp),
                color = colorResource(R.color.white),
                fontSize = 30.sp
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f).padding(start = 45.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            TextField(
                value = loginField.value,
                modifier = Modifier.background(colorResource(R.color.dark_green)),
                placeholder = { Text(text = "Email", color = colorResource(R.color.white)) },
                onValueChange = { newText -> loginField.value = newText }
            )
            TextField(
                value = passwordField.value,
                modifier = Modifier
                    .background(colorResource(R.color.dark_green))
                    .padding(top = 20.dp),
                placeholder = { Text(text = "Пароль", color = colorResource(R.color.white)) },
                onValueChange = { newText -> passwordField.value = newText })
            Button(
                onClick = {
                    Autorization(loginField.value, passwordField.value, navController)
                          },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                modifier = Modifier
                    .fillMaxWidth(0.89f)
                    .fillMaxHeight(0.4f)
                    .padding(top = 30.dp)
                    .alpha(0.80f)
            ) {
                Text(text = "Sign in", color = colorResource(R.color.white))
            }
            TextButton(onClick = {
                fromLogintoRegister(navController)
            }) {
                Text(text = "Register", color = colorResource(R.color.white))
            }
            Button(
                onClick = {
                     Autorization(loginField.value, passwordField.value, navController)
                          },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                modifier = Modifier
                    .fillMaxWidth(0.89f)
                    .fillMaxHeight(0.67f)
                    .padding(top = 4.dp)
                    .alpha(0.80f)
            ) {
                Text(text = "Профиль", color = colorResource(R.color.white))
            }
        }
    }
}
