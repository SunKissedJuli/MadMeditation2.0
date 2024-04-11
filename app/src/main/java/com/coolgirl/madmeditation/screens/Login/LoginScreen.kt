package com.coolgirl.madmeditation.screens.Login
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.coolgirl.madmeditation.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import com.coolgirl.madmeditation.Screen
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel : LoginViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch() {
            viewModel.LoadUserData()
        }
    }

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
                text = stringResource(id = R.string.signin),
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
                .fillMaxHeight(0.65f)
                .padding(start = 45.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(
                value = viewModel.userLogin,
                modifier = Modifier.background(colorResource(R.color.dark_green)),
                placeholder = { Text(text = stringResource(id = R.string.email), color = colorResource(R.color.white)) },
                onValueChange =  { viewModel.updateUserLogin(it) },
            )
            TextField(
                value = viewModel.userPassword,
                modifier = Modifier
                    .background(colorResource(R.color.dark_green))
                    .padding(top = 20.dp),
                placeholder = { Text(text = stringResource(id = R.string.password), color = colorResource(R.color.white)) },
                onValueChange = { viewModel.updateUserPassword(it) }

            )
            Button(
                onClick = {
                    viewModel.Autorization(navController)
                },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                modifier = Modifier
                    .fillMaxWidth(0.89f)
                    .fillMaxHeight(0.4f)
                    .padding(top = 30.dp)
                    .alpha(0.80f)
            ) {
                Text(text = stringResource(id = R.string.signin), color = colorResource(R.color.white))
            }
            TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
                Text(text = stringResource(id = R.string.register), color = colorResource(R.color.white))
            }
            Button(
                onClick = {
                    viewModel.Autorization(navController)
                },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                modifier = Modifier
                    .fillMaxWidth(0.89f)
                    .fillMaxHeight(0.67f)
                    .padding(top = 4.dp)
                    .alpha(0.80f)
            ) {
                Text(text = stringResource(id = R.string.profile), color = colorResource(R.color.white))
            }
        }
    }
}

