package com.coolgirl.madmeditation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.coolgirl.madmeditation.R


fun fromOnBoardingtoLogin(navController: NavHostController){
    navController.navigate("LOGIN")
}
fun fromOnBoardingtoRegiser(navController: NavHostController){
    navController.navigate("REGISTER")
}

@Composable
fun OnBoarding(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight(),
            painter = painterResource(id = R.drawable.background_mountain),
            contentDescription = "image"
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(0.6f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "image"
            )
            Text(text = "ПРИВЕТ",
                color = colorResource(R.color.white)
            )
            Text(text = "Наслаждайся отборочными.\nБудь внимателен. \nДелай хорошо.",
                textAlign = TextAlign.Center,
                color = colorResource(R.color.white)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = { fromOnBoardingtoLogin(navController) },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {
                Text(text = "ВОЙТИ В АККАУНТ",
                    color = colorResource(R.color.white)
                )
            }
            Text(
                modifier = Modifier
                    .clickable { fromOnBoardingtoRegiser(navController) },
                text = "Еще нет аккаунта? Зарегистрируйтесь",
                color = colorResource(R.color.white)
            )
        }
    }
}