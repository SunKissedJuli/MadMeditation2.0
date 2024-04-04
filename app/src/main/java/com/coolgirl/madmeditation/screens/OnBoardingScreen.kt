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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.coolgirl.madmeditation.R


@Composable
fun OnBoardingScreen(navController: NavHostController) {
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
            Text(text = stringResource(id = R.string.hi),
                color = colorResource(R.color.white)
            )
            Text(text = stringResource(id = R.string.enjoy),
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
            Button(onClick = { navController.navigate("LOGIN") },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {
                Text(text = stringResource(id = R.string.singin_rus),
                    color = colorResource(R.color.white)
                )
            }
            Text(
                modifier = Modifier
                    .clickable { navController.navigate("REGISTER") },
                text = stringResource(id = R.string.want_registration),
                color = colorResource(R.color.white)
            )
        }
    }
}