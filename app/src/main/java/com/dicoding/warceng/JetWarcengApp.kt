package com.dicoding.warceng

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.warceng.ui.navigation.NavigationItem
import com.dicoding.warceng.ui.navigation.Screen
import com.dicoding.warceng.ui.screen.cart.CartScreen
import com.dicoding.warceng.ui.screen.category.CategoryScreen
import com.dicoding.warceng.ui.screen.detail.DetailScreen
import com.dicoding.warceng.ui.screen.home.HomeScreen
import com.dicoding.warceng.ui.screen.profile.ProfileScreen
import com.dicoding.warceng.ui.theme.WarcengAppTheme

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map {item->
            NavigationBarItem(
                label = { Text(text = item.title)},
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                }
            )
        }
    }
}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.warceng_order))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.warceng_order)
        )
    )
}

@Composable
fun JetWarcengApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if(currentRoute == Screen.DetailMenu.route){

            }else if(currentRoute == Screen.Category.route){

            }else {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { menuId->
                        navController.navigate(Screen.DetailMenu.createRoute(menuId))
                    },
                    navigateToCategory = { categoryType->
                        navController.navigate(Screen.Category.createRoute(categoryType))
                    }
                )
            }
            composable(Screen.Cart.route) {
                val content = LocalContext.current
                CartScreen(
                    onOrderButtonClicked = {message->
                        shareOrder(content, message)
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailMenu.route,
                arguments = listOf(navArgument("menuId"){
                    type = NavType.LongType
                })
            ){
                val id = it.arguments?.getLong("menuId") ?: -1L
                DetailScreen(
                    menuId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(
                route = Screen.Category.route,
                arguments = listOf(navArgument("typeCategory"){
                    type = NavType.StringType
                })
            ){
                val typeCategory = it.arguments?.getString("typeCategory") ?: ""
                CategoryScreen(
                    category = typeCategory,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToDetail = { menuId->
                        navController.navigate(Screen.DetailMenu.createRoute(menuId))
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun JetWarcengAppPreview() {
    WarcengAppTheme {
        JetWarcengApp()
    }
}