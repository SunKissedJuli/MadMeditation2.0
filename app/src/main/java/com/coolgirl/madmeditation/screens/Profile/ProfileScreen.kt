package com.coolgirl.madmeditation.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.screens.Profile.ProfileViewModel
import com.coolgirl.madmeditation.user
import java.lang.Math.ceil


@Composable
fun ProfileScreen(navController: NavController){
    val viewModel : ProfileViewModel = viewModel()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(R.color.dark_green))
    ) {

        var abracadabra = listOf<Int>(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
        )

        SetHead(navController, viewModel)
        SetUserHead()
        SetPhotoBlock(navController, viewModel, abracadabra)
        SetBottomPanel(navController, viewModel)
    }
}

@Composable
fun SetHead(navController: NavController, viewModel: ProfileViewModel){
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
            modifier = Modifier.fillMaxWidth(0.25f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image"
        )
        Text (
            modifier = Modifier.clickable{ navController.navigate("MAIN")},
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
@Composable
fun SetPhotoBlock(navController: NavController, viewModel: ProfileViewModel, userPhoto : List<Int>) {
    var i = 0
    var count = 2
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(0.86f).fillMaxWidth()
    ) {
        items(userPhoto.size/2) {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {

                if(i+1 > userPhoto.size){count=1}
                items(count) {
                    Card(
                        modifier = Modifier
                            .padding(25.dp, 20.dp, 0.dp, 10.dp)
                            .height(100.dp)
                            .width(150.dp),
                        shape = RoundedCornerShape(15.dp),
                        elevation = 5.dp
                    ) {
                        Image(
                            painter = painterResource(userPhoto[i]),
                            contentDescription = "image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { viewModel.fromProfiletoPhoto(navController) })
                    }
                    i++
                }
            }
        }
    }
}
