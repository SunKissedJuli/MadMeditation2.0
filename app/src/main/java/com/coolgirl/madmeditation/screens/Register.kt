package com.coolgirl.madmeditation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.coolgirl.madmeditation.R

@Composable
fun Register(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_green)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.will_be_register), color = colorResource(R.color.white),
            fontSize = 30.sp)
    }
}