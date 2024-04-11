package com.coolgirl.madmeditation

sealed class Screen(val route: String){
    object OnBoarding : Screen("onboarding_screen")

    object Login : Screen("login_screen")

    object Register : Screen("register_screen")

    object Photo : Screen("photo_screen/{user_photo_id}"){
        fun user_photo_id(user_photo_id : Int) : String{
            return "photo_screen/$user_photo_id"
        }
    }

    object Profile : Screen("profile_screen")

    object Main : Screen("main_screen")

    object Menu : Screen("menu_screen")
}