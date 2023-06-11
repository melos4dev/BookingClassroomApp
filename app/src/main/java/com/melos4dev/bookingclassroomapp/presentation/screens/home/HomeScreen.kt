package com.melos4dev.bookingclassroomapp.presentation.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.melos4dev.bookingclassroomapp.presentation.navigation.HomeBottomBarNavGraph
import com.melos4dev.bookingclassroomapp.presentation.navigation.HomeBottomBarScreen
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    Scaffold(
        topBar = { },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            HomeBottomBarNavGraph(navController = navController)
        }
    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        HomeBottomBarScreen.MyBookings,
        HomeBottomBarScreen.Notifications,
        HomeBottomBarScreen.MyIncidences,
        HomeBottomBarScreen.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {

        NavigationBar(containerColor = DarkPrimaryColor) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }

    }

}
@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = { Icon(imageVector = screen.icon, contentDescription = "Navigation icon") },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor= DarkPrimaryColor,
            selectedTextColor= Color.White,
            unselectedIconColor = Color.DarkGray,
            unselectedTextColor = Color.DarkGray,
            indicatorColor= Color.White
        ),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }

        }
    )
}
