package com.coolgirl.madmeditation

enum class Screen {
    ONBOARDING,
    LOGIN,
    REGISTER,
    PHOTO,
    PROFILE,
    MAIN,
    MENU
}

sealed class NavigationItem(val route: String){
    object OnBoarding : NavigationItem(Screen.ONBOARDING.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object Photo : NavigationItem(Screen.PHOTO.name)
    object Profile : NavigationItem(Screen.PROFILE.name)
    object Main : NavigationItem(Screen.MAIN.name)
    object Menu : NavigationItem(Screen.MENU.name)
}