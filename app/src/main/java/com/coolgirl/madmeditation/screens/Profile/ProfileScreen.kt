package com.coolgirl.madmeditation.screens

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.screens.Profile.ImageData
import com.coolgirl.madmeditation.screens.Profile.ProfileViewModel
import kotlin.math.roundToInt


@Composable
fun ProfileScreen(navController: NavController){
    val viewModel : ProfileViewModel = viewModel()
    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(colorResource(R.color.dark_green))
    ) {
        SetHead(navController, viewModel)
        SetUserHead(viewModel)
        SetPhotoBlock(navController, viewModel, viewModel.NewImage())
        SetBottomPanel(navController, viewModel)
    }
}

@Composable
fun SetHead(navController: NavController, viewModel: ProfileViewModel){
    Row(
        modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.25f),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.hamburger),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(20.dp)
                .clickable { navController.navigate("MENU") })

        Image(
            modifier = Modifier.fillMaxWidth(0.25f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image")
        Text (
            modifier = Modifier.clickable{ navController.navigate("MAIN")},
            text = stringResource(id = R.string.exit),
            color = colorResource(R.color.white),)
    }
}

@Composable
fun SetUserHead(viewModel: ProfileViewModel){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.32f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberImagePainter(viewModel.GetUser()?.avatar),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape))
        Text(text = viewModel.GetUser()?.nickName.toString(), color = colorResource(R.color.white), fontSize = 30.sp)
    }
}
@Composable
fun SetBottomPanel(navController: NavController, viewModel: ProfileViewModel){
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Image(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .alpha(0.3f)
                .clickable { navController.navigate("MAIN") },
            painter = painterResource(R.drawable.logo),
            contentDescription = "image")
        Image(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .size(20.dp),
            painter = painterResource(R.drawable.sound_icon),
            contentDescription = "image")
        Image(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .size(20.dp),
            painter = painterResource(R.drawable.profile_icon),
            contentDescription = "image")
    }
}

@Composable
fun SetPhotoBlock(navController: NavController, viewModel: ProfileViewModel, launcher: ManagedActivityResultLauncher<String, Uri?>) {
    key(viewModel.imagee){
        val columnItems : Int = ((viewModel.SetImageList().size).toFloat()/2).roundToInt()
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight(0.86f)
                .fillMaxWidth()
        ) {
            items(columnItems) { columnIndex ->
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    val count = if (columnIndex == columnItems - 1 && viewModel.SetImageList().size % 2 != 0) 1 else 2
                    items(count) { rowIndex ->
                        val currentIndex = columnIndex * 2 + rowIndex
                        Card(
                            modifier = Modifier
                                .padding(25.dp, 20.dp, 0.dp, 10.dp)
                                .height(100.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(15.dp),
                            elevation = 5.dp) {
                            Image(
                                painter = when (val image = viewModel.SetImageList()[currentIndex]) {
                                    is ImageData.ImageResource -> rememberImagePainter(image.resourceId)
                                    is ImageData.ImageUri -> rememberImagePainter(image.uri)
                                    else -> { rememberImagePainter(R.drawable.sorry)} },
                                contentDescription = "image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { viewModel.fromProfiletoPhoto(navController, currentIndex) })
                        }
                    }
                }
            }
            item(1){
                Card(
                    modifier = Modifier
                        .padding(25.dp, 20.dp, 0.dp, 10.dp)
                        .height(100.dp)
                        .width(150.dp),
                    shape = RoundedCornerShape(15.dp),
                    backgroundColor = colorResource(id = R.color.light_green),
                    elevation = 5.dp) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "+",
                            color = colorResource(id = R.color.white),
                            fontSize = 30.sp,
                        modifier = Modifier
                                .clickable { launcher.launch("image/*")})
                    }
            }
        }}
    }
}