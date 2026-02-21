package com.edwinkapkei.formula1.views.drivers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.data.model.constructor.Constructor
import com.edwinkapkei.formula1.data.model.driver.Driver
import com.edwinkapkei.formula1.data.model.driver.DriverAndImage
import com.edwinkapkei.formula1.data.model.driver.DriverStanding
import com.edwinkapkei.formula1.utilities.CustomDateFormatter.getCurrentYear
import com.edwinkapkei.formula1.views.common.ErrorDialog
import com.edwinkapkei.formula1.views.common.LoadingScreen
import com.edwinkapkei.formula1.views.theme.Formula1Theme
import com.edwinkapkei.formula1.views.viewmodel.DriversViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriversScreen(
    viewModel: DriversViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCurrentDrivers(getCurrentYear())
    }

    PullToRefreshBox(
        isRefreshing = uiState.isLoading && uiState.drivers.isNotEmpty(),
        onRefresh = { viewModel.getCurrentDrivers(getCurrentYear()) },
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.isLoading && uiState.drivers.isEmpty()) {
            LoadingScreen()
        } else {
            DriversList(uiState.drivers)
        }
    }

    uiState.error?.let { message ->
        ErrorDialog(
            errorMessage = message,
            onDismiss = { viewModel.clearError() }
        )
    }
}

@Composable
fun DriversList(drivers: List<DriverAndImage>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        itemsIndexed(drivers) { index, driver ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${index + 1}", fontWeight = FontWeight.W500, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${driver.driverStanding.driver.givenName} ${driver.driverStanding.driver.familyName}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = stringResource(R.string.points, driver.driverStanding.points),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    AsyncImage(
                        modifier = Modifier.size(80.dp),
                        model = driver.driverImageUrl,
                        contentDescription = "driver image",
                        placeholder = painterResource(R.drawable.racing_helmet_blue)
                    )
                }
                HorizontalDivider(color = Color(0xFFE2E2E2))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDriversList() {
    Formula1Theme {
        DriversList(drivers = getSampleDrivers())
    }
}

fun getSampleDrivers(): List<DriverAndImage> {
    return listOf(
        DriverAndImage(
            driverStanding = DriverStanding(
                constructors = listOf(
                    Constructor(
                        constructorId = "red_bull",
                        name = "Red Bull Racing",
                        nationality = "Austrian",
                        url = "https://en.wikipedia.org/wiki/Red_Bull_Racing"
                    )
                ),
                driver = Driver(
                    code = "VER",
                    dateOfBirth = "1997-09-30",
                    driverId = "max_verstappen",
                    familyName = "Verstappen",
                    givenName = "Max",
                    nationality = "Dutch",
                    permanentNumber = "33",
                    url = "https://en.wikipedia.org/wiki/Max_Verstappen"
                ),
                points = "454",
                position = "1",
                positionText = "1",
                wins = "15"
            ),
            driverImageUrl = "https://example.com/verstappen.png"
        ),
        DriverAndImage(
            driverStanding = DriverStanding(
                constructors = listOf(
                    Constructor(
                        constructorId = "mercedes",
                        name = "Mercedes",
                        nationality = "German",
                        url = "https://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One"
                    )
                ),
                driver = Driver(
                    code = "HAM",
                    dateOfBirth = "1985-01-07",
                    driverId = "hamilton",
                    familyName = "Hamilton",
                    givenName = "Lewis",
                    nationality = "British",
                    permanentNumber = "44",
                    url = "https://en.wikipedia.org/wiki/Lewis_Hamilton"
                ),
                points = "240",
                position = "2",
                positionText = "2",
                wins = "0"
            ),
            driverImageUrl = "https://example.com/hamilton.png"
        )
    )
}
