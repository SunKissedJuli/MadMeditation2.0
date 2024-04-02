package com.coolgirl.madmeditation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.user


@Composable
fun Profile(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(R.color.dark_green))
    ) {

        SetHead(navController)
        SetUserHead()
        SetPhotoBlock(navController)
        SetBottomPanel(navController)
    }
}

fun fromProfiletoMenu(navController: NavController){ navController.navigate("MENU") }

fun fromProfiletoMain(navController: NavController){ navController.navigate("MAIN") }

//он научится принимать аргумент фото
fun fromProfiletoPhoto(navController: NavController){ navController.navigate("PHOTO") }

@Composable
fun SetHead(navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.25f),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.hamburger),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { fromProfiletoMenu(navController) })
        }
        Image(
            modifier = Modifier.fillMaxWidth(0.25f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image"
        )
        Text (
            modifier = Modifier.clickable{ fromProfiletoMain(navController)},
            text = "exit",
            color = colorResource(R.color.white),)
    }
}

@Composable
fun SetUserHead(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.32f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberImagePainter(user?.avatar),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape))
        Text(text = user?.nickName.toString(), color = colorResource(R.color.white), fontSize = 30.sp)
    }
}
@Composable
fun SetBottomPanel(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Image(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .alpha(0.3f)
                .clickable { fromProfiletoMain(navController) },
            painter = painterResource(R.drawable.logo),
            contentDescription = "image"
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .size(20.dp),
            painter = painterResource(R.drawable.sound_icon),
            contentDescription = "image"
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .size(20.dp),
            painter = painterResource(R.drawable.profile_icon),
            contentDescription = "image"
        )
    }
}
//это будет подвергнуто рефакторингу
@Composable
fun SetPhotoBlock(navController: NavController){
    Column(modifier = Modifier
        .fillMaxHeight(0.86f)
        .fillMaxWidth()
        .verticalScroll(ScrollState(1), true)) {
        Row(
            modifier = Modifier.
            fillMaxWidth()){
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(20.dp, 20.dp, 20.dp, 20.dp)
                    .height(100.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp){
                Image(
                    painter = painterResource(R.drawable.image1),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clickable { fromProfiletoPhoto(navController) })
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 20.dp)
                    .height(100.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp) {
                Image(
                    painter = painterResource(R.drawable.image2),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image",
                    modifier = Modifier.fillMaxSize().clickable { fromProfiletoPhoto(navController) })
            }
        }
        Row(
            modifier = Modifier.
            fillMaxWidth()){
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(20.dp, 20.dp, 20.dp, 20.dp)
                    .height(100.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp){
                Image(
                    painter = painterResource(R.drawable.image3),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clickable { fromProfiletoPhoto(navController) })
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 20.dp)
                    .height(100.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp) {
                Image(
                    painter = painterResource(R.drawable.image4),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image",
                    modifier = Modifier.fillMaxSize().clickable { fromProfiletoPhoto(navController) })
            }
        }
        Row(
            modifier = Modifier.
            fillMaxWidth()){
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(20.dp, 20.dp, 20.dp, 20.dp)
                    .height(100.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(R.color.light_green))) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "+", fontSize = 40.sp, color = colorResource(R.color.white), fontWeight = FontWeight.Bold,)
                        //    modifier = Modifier.clickable { Toast.makeText(context, "Добавление ещё не завезли", Toast.LENGTH_SHORT).show() })
                    }

                }
            }
        }
    }
}