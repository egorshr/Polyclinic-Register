package com.example.polyclinicregister.presentation.navgraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.polyclinicregister.presentation.employee.EmployeeScreen
import com.example.polyclinicregister.presentation.employee.EmployeeViewModel
import com.example.polyclinicregister.presentation.service.ServiceScreen
import com.example.polyclinicregister.presentation.service.ServiceViewModel
import com.example.polyclinicregister.presentation.visit.VisitScreen
import com.example.polyclinicregister.presentation.visit.VisitViewModel
import com.example.polyclinicregister.ui.theme.PolyclinicRegisterTheme
import org.koin.androidx.compose.koinViewModel


data class BottomNavigationItem(val name: String, val route: Route, val icon: ImageVector)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolyclinicRegisterNavGraph(modifier: Modifier = Modifier) {

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            name = "Сотрудники",
            route = Route.EmployeeScreen,
            icon = Icons.Default.AccountCircle
        ),
        BottomNavigationItem(
            name = "Посещения",
            route = Route.VisitScreen,
            icon = Icons.Default.DateRange
        ),
        BottomNavigationItem(
            name = "Услуги",
            route = Route.ServiceScreen,
            icon = Icons.Default.ShoppingCart
        )
    )

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = remember(key1 = navBackStackEntry) {
        bottomNavigationItems.indexOfFirst { item ->
            currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true
        }.let { if (it == -1) 0 else it }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = bottomNavigationItems[selectedItem].name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp
                    )
                }
            )
        },
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            destination = Route.EmployeeScreen
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            destination = Route.VisitScreen
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            destination = Route.ServiceScreen
                        )
                    }
                }
            )
        }
    )
    { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.EmployeeScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.EmployeeScreen> {
                val viewModel = koinViewModel<EmployeeViewModel>()
                val state by viewModel.state.collectAsState()
                EmployeeScreen(state = state)
            }

            composable<Route.VisitScreen> {
                val viewModel = koinViewModel<VisitViewModel>()
                val state by viewModel.state.collectAsState()
                VisitScreen(state = state)
            }

            composable<Route.ServiceScreen> {
                val viewModel = koinViewModel<ServiceViewModel>()
                val state by viewModel.state.collectAsState()
                ServiceScreen(state = state)
            }
        }
    }


}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,

                            )
                        Text(text = item.name)
                    }
                },
            )
        }
    }
}

private fun navigateToTab(navController: NavController, destination: Route) {
    navController.navigate(destination) {
        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
private fun dfjkdf() {
    PolyclinicRegisterTheme {
        PolyclinicRegisterNavGraph()
    }
}

