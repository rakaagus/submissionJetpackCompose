package com.dicoding.warceng.ui.navigation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Cart: Screen("cart")
    object Profile: Screen("profile")
    object Category: Screen("category/{typeCategory}"){
        fun createRoute(typeCategory: String) = "category/$typeCategory"
    }
    object DetailMenu: Screen("home/{menuId}"){
        fun createRoute(menuId: Long) = "home/$menuId"
    }
}