package com.coolgirl.madmeditation.screens

import android.widget.ImageView.ScaleType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.screens.Photo.PhotoViewModel

@Composable
fun PhotoScreen(navController: NavController, userPhoto : Int){
    val viewModel : PhotoViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_green)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SetPhoto(userPhoto)
        SetPhotoBottomPanel(navController)
    }
}

@Composable
fun SetPhoto(userPhoto: Int){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = userPhoto),
            contentDescription = "image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun SetPhotoBottomPanel(navController: NavController){
    Row(modifier = Modifier.fillMaxSize(),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically){
        Text(text = stringResource(id = R.string.delite), color = colorResource(id = R.color.white))
        Text(text = stringResource(id = R.string.close), color = colorResource(id = R.color.white),
            modifier = Modifier.clickable { navController.navigate("PROFILE") })
    }
}