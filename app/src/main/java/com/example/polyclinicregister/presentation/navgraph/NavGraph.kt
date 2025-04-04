package com.example.polyclinicregister.presentation.navgraph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.polyclinicregister.R
import com.example.polyclinicregister.presentation.employee.EmployeeScreen
import com.example.polyclinicregister.presentation.employee.EmployeeViewModel
import com.example.polyclinicregister.presentation.service.ServiceScreen
import com.example.polyclinicregister.presentation.service.ServiceViewModel
import com.example.polyclinicregister.presentation.visit.VisitScreen
import com.example.polyclinicregister.presentation.visit.VisitViewModel
import org.koin.androidx.compose.koinViewModel


data class BottomNavigationItem(val name: String, val route: Route, val icon: Painter)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolyclinicRegisterNavGraph(modifier: Modifier = Modifier) {

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            name = "Сотрудники",
            route = Route.EmployeeScreen,
            icon = painterResource(R.drawable.groups_24px)
        ),
        BottomNavigationItem(
            name = "Посещения",
            route = Route.VisitScreen,
            icon = painterResource(R.drawable.event_note_24px)
        ),
        BottomNavigationItem(
            name = "Услуги",
            route = Route.ServiceScreen,
            icon = painterResource(R.drawable.medical_services_24px)
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
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = bottomNavigationItems[selectedItem].name,
                        style = MaterialTheme.typography.displaySmall
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
            modifier = Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable<Route.EmployeeScreen> {
                val viewModel = koinViewModel<EmployeeViewModel>()
                val state by viewModel.state.collectAsState()
                EmployeeScreen(
                    state = state,
                    onDeleteEmployee = { id ->
                        viewModel.deleteEmployee(id)
                    }
                )
            }

            composable<Route.VisitScreen> {
                val viewModel = koinViewModel<VisitViewModel>()
                val state by viewModel.state.collectAsState()
                VisitScreen(
                    state = state,
                    onVisitDelete = { id ->
                        viewModel.deleteVisit(id)
                    },
                    onDateRangeSelected = {
                        viewModel.onSelectDateRange(it)
                    },
                    onFilter = {
                        viewModel.onFilter()
                    },
                    onDismiss = {
                        viewModel.onDismiss()
                    },
                    onVisitCheck = { isChecked, id ->
                        viewModel.onCheckChange(isChecked, id)
                    },
                    onDeleteSelected = {
                        viewModel.deleteSelectedVisits()
                    }
                )
            }

            composable<Route.ServiceScreen> {
                val viewModel = koinViewModel<ServiceViewModel>()
                val state by viewModel.state.collectAsState()
                ServiceScreen(
                    state = state,
                    onDeleteService = { id ->
                        viewModel.deleteService(id)
                    },
                    onUpdateService = { service ->
                        viewModel.updateService(service)
                    },
                    onDescendingPrice = {
                        viewModel.getServices("desc")
                    },
                    onAscendingPrice = {
                        viewModel.getServices("asc")
                    },

                )
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
                            painter = item.icon,
                            contentDescription = null,

                            )
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium
                        )
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

