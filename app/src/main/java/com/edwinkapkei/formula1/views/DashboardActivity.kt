package com.edwinkapkei.formula1.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.views.constructors.TeamsScreen
import com.edwinkapkei.formula1.views.drivers.DriversScreen
import com.edwinkapkei.formula1.views.schedule.ScheduleScreen
import com.edwinkapkei.formula1.views.theme.Formula1Theme
import com.edwinkapkei.formula1.views.theme.Gray
import com.edwinkapkei.formula1.views.theme.PrimaryBlue

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Formula1Theme {
                DashboardNavigationBar()
            }
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
                modifier = Modifier.shadow(elevation = 4.dp),
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.shadow(8.dp),
                windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = MaterialTheme.colorScheme.background,
                tonalElevation = 8.dp
            ) {
                DashboardRoutes.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(destination.icon),
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(stringResource(destination.label)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            unselectedIconColor = Gray,
                            indicatorColor = Color(0xFFDEEFF8)
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        DashboardNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(contentPadding)
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
        modifier = modifier
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
    val route: String,
    val label: Int,
    val icon: Int,
    val contentDescription: String
) {
    Schedule(
        route = "schedule",
        label = R.string.schedule,
        icon = R.drawable.baseline_today_24,
        contentDescription = "Schedule"
    ),
    Drivers(
        route = "drivers",
        label = R.string.drivers,
        icon = R.drawable.racing_helmet,
        contentDescription = "Schedule"
    ),
    Teams(
        route = "teams",
        label = R.string.teams,
        icon = R.drawable.account_group,
        contentDescription = "Schedule"
    )
}
