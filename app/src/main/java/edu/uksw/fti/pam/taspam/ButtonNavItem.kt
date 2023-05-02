package edu.uksw.fti.pam.taspam

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

open class ButtonNavItem (
    val title: String,
    val icon: ImageVector,
    val screen_route: String,
){
    object Home: ButtonNavItem("Home", Icons.Default.Home, screen_route = "Home")
    object Search: ButtonNavItem("Search", Icons.Default.Search, screen_route = "Search")
    object Filter: ButtonNavItem("Filter", Icons.Default.List, screen_route = "Filter")
    object Profile: ButtonNavItem("Profile", Icons.Default.AccountCircle, screen_route = "Profile")
}