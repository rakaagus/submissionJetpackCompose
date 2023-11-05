package com.dicoding.warceng.ui.navigation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Cart: Screen("cart")
    object Favorite: Screen("favorite")
    object Profile: Screen("profile")
    object DetailMenu: Screen("home/{menuId}"){
        fun createRoute(menuId: Long) = "home/$menuId"
    }
}