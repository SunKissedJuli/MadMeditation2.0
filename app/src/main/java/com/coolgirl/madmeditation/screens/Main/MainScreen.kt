package com.coolgirl.madmeditation.screens

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.coolgirl.madmeditation.Models.*
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.screens.Main.MainViewModel

@Composable
fun MainScreen(navController: NavController){
    var viewModel: MainViewModel = viewModel()
    viewModel.LoadFeelings()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(R.color.dark_green))
    ) {
        SetMainHead(navController, viewModel)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ){
            viewModel.GetFeelings()?.let { items(it.size){
                SetHorizontallScroll(viewModel) }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .verticalScroll(ScrollState(1), true),
            verticalArrangement = Arrangement.Top){
            ScrollItemBlock(R.drawable.iconforblock1)
            ScrollItemBlock(R.drawable.iconforblock2)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalAlignment = Alignment.Bottom){
            SetMainBottomPanel(navController, viewModel)
        }
    }
}

@Composable
fun SetHorizontallScroll(viewModel: MainViewModel){
    viewModel.GetFeelings()?.let { feelingsList ->
        for (i in 0 until feelingsList.size) {
            val feel = feelingsList[i]
            feel.title?.let { title ->
                feel.image?.let { image ->
                    ScrollItemMindset(title, image)
                }
            }
        }
    }
}

@Composable
fun SetMainHead(navController: NavController, viewModel: MainViewModel){
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
                    .clickable { navController.navigate("MENU") })
        }
        Image(
            modifier = Modifier
                .fillMaxWidth(0.25f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image"
        )
        Image(
            painter = rememberImagePainter( viewModel.GetUser()?.avatar),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable { navController.navigate("PROFILE") }
                .size(40.dp)
                .clip(CircleShape)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){
        Text(text = (stringResource(id = R.string.welcome_back) +" ${ viewModel.GetUser()?.nickName}!"), color = colorResource(R.color.white), fontSize = 25.sp)
        Text(text = stringResource(id =R.string.how_r_u), color = colorResource(R.color.white))
    }
}

@SuppressLint("ResourceType")
@Composable
fun ScrollItemMindset(itemName:String, img : String){
    Column(modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .padding(13.dp, 5.dp, 13.dp, 5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(colorResource(R.color.white))) {
                Image(
                    painter = rememberImagePainter(img),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(7.dp)
                        .size(55.dp)) } }
        Text(text = itemName, color = colorResource(R.color.white), fontSize = 12.sp, modifier = Modifier)
    }
}

@Composable
fun ScrollItemBlock(@DrawableRes img: Int){
    Card(
        modifier = Modifier
            .padding(20.dp, 20.dp, 20.dp, 20.dp)
            .height(160.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(colorResource(R.color.white))) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column( verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = stringResource(id = R.string.mainblock_heading), color = colorResource(R.color.dark_green), fontSize = 23.sp)
                    Text(text = stringResource(id = R.string.mainblock_description), color = colorResource(R.color.dark_green), fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 15.dp))
                    Button(onClick = {  },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                        Text(text = stringResource(id = R.string.mainblock_more),color = colorResource(R.color.white))
                    }
                }
                Image(
                    painter = painterResource(img),
                    contentDescription = "image")
            }
        }
    }
}

@Composable
fun SetMainBottomPanel(navController: NavController, viewModel: MainViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Image(
            modifier = Modifier.fillMaxHeight(0.8f),
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
                .alpha(0.5f)
                .size(20.dp)
                .clickable { navController.navigate("Profile") },
            painter = painterResource(R.drawable.profile_icon),
            contentDescription = "image"
        )
    }
}