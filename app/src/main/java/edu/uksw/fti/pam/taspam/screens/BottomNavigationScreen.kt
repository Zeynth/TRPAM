package edu.uksw.fti.pam.taspam.screens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.uksw.fti.pam.taspam.ButtonNavItem
import edu.uksw.fti.pam.taspam.model.*
import edu.uksw.fti.pam.taspam.screens.filter.FilterScreen
import edu.uksw.fti.pam.taspam.screens.filter.JatengScreen
import edu.uksw.fti.pam.taspam.screens.filter.NTBScreen
import edu.uksw.fti.pam.taspam.screens.filter.SumbarScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    searchViewModel: SearchViewModel = SearchViewModel(),
    ntbViewModel: NTBViewModel = NTBViewModel(),
    jatengViewModel: JatengViewModel = JatengViewModel(),
    sumbarViewModel: SumbarViewModel = SumbarViewModel(),
    sharedViewModel: SharedViewModel,
) {
    val lContext = LocalContext.current
//    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(
        navController = navController,
//        startDestination = Screens.LoginScreen.route
        startDestination = ButtonNavItem.Home.screen_route
    ) {
        composable(route = Screens.SumbarScreen.route) {
            SumbarScreen(viewModel = sumbarViewModel, navController = navController)
        }
        composable(route = Screens.JatengScreen.route) {
            JatengScreen(viewModel = jatengViewModel, navController = navController)
        }
        composable(route = Screens.NTBScreen.route) {
            NTBScreen(viewModel = ntbViewModel, navController = navController)
        }
        composable(route = Screens.ChangePasswordScreen.route) {
            ChangePasswordScreen(navController = navController, saveViewModel = sharedViewModel)
        }
        composable(Screens.NavGraphScreen.route) {
            edu.uksw.fti.pam.taspam.navigation.NavigationGraph()
        }
        composable(ButtonNavItem.Home.screen_route) {
//            DefaultPreview()
//            LandingPage(avm = vm1)
            HomeScreen(popularViewModel = searchViewModel, recommendViewModel = jatengViewModel, navController = navController)
        }
        composable(ButtonNavItem.Search.screen_route) {
            SearchScreen(searchViewModel = searchViewModel, navController = navController)
//            SearchScreen()
        }
        composable(ButtonNavItem.Filter.screen_route) {
            FilterScreen(navController = navController)
//            TrendScreenPreview() {
//                    lContext.startActivity(AnimeProfileActivity.newIntent(lContext, it))
//            }
        }
        composable(ButtonNavItem.Profile.screen_route) {
            ProfileScreen(navController = navController, loadViewModel = sharedViewModel)
        }
        composable(
            route = "Detail" + "?id={id}?title={title}?image={image}?bahan={bahan}?desc={desc}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("image") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("genre") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("desc") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry: NavBackStackEntry ->
            DetailScreen(
                id = navBackStackEntry.arguments?.getString("id") ,
                title = navBackStackEntry.arguments?.getString("title") ,
                image = navBackStackEntry.arguments?.getString("image") ,
                bahan = navBackStackEntry.arguments?.getString("bahan") ,
                desc = navBackStackEntry.arguments?.getString("desc")
            )
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavController
){
    val items = listOf(
        ButtonNavItem.Home,
        ButtonNavItem.Search,
        ButtonNavItem.Filter,
        ButtonNavItem.Profile
    )
    androidx.compose.material.BottomNavigation(
        //backgroundColor = colorResource(id = R.color.teal_200),
        backgroundColor = Color(231,79,80),
        contentColor = Color(3, 15, 40)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    imageVector = item.icon,
                    contentDescription = "${item.title} icon")
                },
                label = {
                    Text(text = item.title,
                        fontSize = 9.sp)
                },
                selectedContentColor = Color.White.copy(1.0f),
                unselectedContentColor = Color.White.copy(0.5f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationMainScreenView(sharedViewModel: SharedViewModel){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
            )
        }
    ) {
        NavigationGraph(
            navController = navController,
            sharedViewModel = sharedViewModel,
        )
    }
}