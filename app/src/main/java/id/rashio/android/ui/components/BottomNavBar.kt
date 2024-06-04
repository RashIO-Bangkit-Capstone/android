package id.rashio.android.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(
    navController: NavController,
    items: List<BottomNavigationItem>
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var selectedIndex = items.indexOfFirst { it.route == currentDestination?.route }


    NavigationBar {
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == bottomNavigationItem.route } == true,
                onClick = {
                    selectedIndex = index
                    navController.navigate(bottomNavigationItem.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedIndex) {
                            bottomNavigationItem.selectedIcon
                        } else bottomNavigationItem.unselectedIcon,
                        contentDescription = bottomNavigationItem.title
                    )
                },
                label = { Text(text = bottomNavigationItem.title) },
                alwaysShowLabel = true
            )
        }
    }
}
