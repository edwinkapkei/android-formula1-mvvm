package com.edwinkapkei.formula1.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Groups3
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.views.constructors.TeamsScreen
import com.edwinkapkei.formula1.views.drivers.DriversScreen
import com.edwinkapkei.formula1.views.schedule.ScheduleScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DashboardNavigationBar()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardNavigationBar() {
    val navController = rememberNavController()
    val startDestination = DashboardRoutes.Schedule
    var selectedDestination by remember { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                DashboardRoutes.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        DashboardNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(contentPadding),
        )
    }
}

@Composable
fun DashboardNavHost(
    navController: NavHostController,
    startDestination: DashboardRoutes,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        DashboardRoutes.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    DashboardRoutes.Schedule -> ScheduleScreen()
                    DashboardRoutes.Drivers -> DriversScreen()
                    DashboardRoutes.Teams -> TeamsScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDashboardNavigationBar() {
    DashboardNavigationBar()
}


enum class DashboardRoutes(
    val route: String, val label: String,
    val icon: ImageVector, val contentDescription: String
) {
    Schedule(
        route = "schedule",
        label = "Schedule",
        icon = Icons.Filled.Schedule,
        contentDescription = "Schedule"
    ),
    Drivers(
        route = "drivers",
        label = "Drivers",
        icon = Icons.Filled.ChildCare,
        contentDescription = "Schedule"
    ),
    Teams(
        route = "teams",
        label = "Teams",
        icon = Icons.Filled.Groups3,
        contentDescription = "Schedule"
    )
}