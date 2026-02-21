package com.edwinkapkei.formula1.views.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.edwinkapkei.formula1.data.model.schedule.Circuit
import com.edwinkapkei.formula1.data.model.schedule.FirstPractice
import com.edwinkapkei.formula1.data.model.schedule.Location
import com.edwinkapkei.formula1.data.model.schedule.Qualifying
import com.edwinkapkei.formula1.data.model.schedule.Race
import com.edwinkapkei.formula1.data.model.schedule.SecondPractice
import com.edwinkapkei.formula1.data.model.schedule.Sprint
import com.edwinkapkei.formula1.data.model.schedule.ThirdPractice
import com.edwinkapkei.formula1.utilities.CustomDateFormatter.getCurrentYear
import com.edwinkapkei.formula1.views.common.ErrorDialog
import com.edwinkapkei.formula1.views.common.LoadingScreen
import com.edwinkapkei.formula1.views.viewmodel.ScheduleViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCurrentSchedule(getCurrentYear())
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            LoadingScreen()
        } else {
            ScheduleList(uiState.races)
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
fun ScheduleList(races: List<Race>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        items(races) { race ->
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = formatter.parse(race.date)
            val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
            val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                date?.let {
                    val day = dayFormat.format(it)
                    val month = monthFormat.format(it)
                    ElevatedCard {
                        Column(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(day)
                            Text(month)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(race.circuit.location.country)
                    Text(race.raceName)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewScheduleList() {
    ScheduleList(races = getSchedule())
}

fun getSchedule(): List<Race> {
    return listOf(
        Race(
            circuit = Circuit(
                circuitId = "albert_park",
                circuitName = "Albert Park Grand Prix Circuit",
                location = Location(
                    lat = "-37.8497",
                    long = "144.968",
                    locality = "Melbourne",
                    country = "Australia"
                ),
                url = "https://en.wikipedia.org/wiki/Melbourne_Grand_Prix_Circuit"
            ),
            date = "2025-03-16",
            firstPractice = FirstPractice(
                date = "2025-03-14",
                time = "02:30:00Z"
            ),
            qualifying = Qualifying(
                date = "2025-03-15",
                time = "06:00:00Z"
            ),
            raceName = "Australian Grand Prix",
            round = "1",
            season = "2025",
            secondPractice = SecondPractice(
                date = "2025-03-14",
                time = "06:00:00Z"
            ),
            sprint = null,
            thirdPractice = ThirdPractice(
                date = "2025-03-15",
                time = "02:30:00Z"
            ),
            time = "05:00:00Z",
            url = "https://en.wikipedia.org/wiki/2025_Australian_Grand_Prix"
        ),
        Race(
            circuit = Circuit(
                circuitId = "silverstone",
                circuitName = "Silverstone Circuit",
                location = Location(
                    lat = "52.0786",
                    long = "-1.0169",
                    locality = "Silverstone",
                    country = "UK"
                ),
                url = "https://en.wikipedia.org/wiki/Silverstone_Circuit"
            ),
            date = "2025-07-06",
            firstPractice = FirstPractice(
                date = "2025-07-04",
                time = "11:30:00Z"
            ),
            qualifying = Qualifying(
                date = "2025-07-05",
                time = "14:00:00Z"
            ),
            raceName = "British Grand Prix",
            round = "12",
            season = "2025",
            secondPractice = SecondPractice(
                date = "2025-07-04",
                time = "15:00:00Z"
            ),
            sprint = Sprint(
                date = "2025-07-05",
                time = "11:00:00Z"
            ),
            thirdPractice = null,
            time = "14:00:00Z",
            url = "https://en.wikipedia.org/wiki/2025_British_Grand_Prix"
        ),
        Race(
            circuit = Circuit(
                circuitId = "albert_park",
                circuitName = "Albert Park Grand Prix Circuit",
                location = Location(
                    lat = "-37.8497",
                    long = "144.968",
                    locality = "Melbourne",
                    country = "Australia"
                ),
                url = "https://en.wikipedia.org/wiki/Melbourne_Grand_Prix_Circuit"
            ),
            date = "2025-03-16",
            firstPractice = FirstPractice(
                date = "2025-03-14",
                time = "02:30:00Z"
            ),
            qualifying = Qualifying(
                date = "2025-03-15",
                time = "06:00:00Z"
            ),
            raceName = "Australian Grand Prix",
            round = "1",
            season = "2025",
            secondPractice = SecondPractice(
                date = "2025-03-14",
                time = "06:00:00Z"
            ),
            sprint = null,
            thirdPractice = ThirdPractice(
                date = "2025-03-15",
                time = "02:30:00Z"
            ),
            time = "05:00:00Z",
            url = "https://en.wikipedia.org/wiki/2025_Australian_Grand_Prix"
        ),
        Race(
            circuit = Circuit(
                circuitId = "silverstone",
                circuitName = "Silverstone Circuit",
                location = Location(
                    lat = "52.0786",
                    long = "-1.0169",
                    locality = "Silverstone",
                    country = "UK"
                ),
                url = "https://en.wikipedia.org/wiki/Silverstone_Circuit"
            ),
            date = "2025-07-06",
            firstPractice = FirstPractice(
                date = "2025-07-04",
                time = "11:30:00Z"
            ),
            qualifying = Qualifying(
                date = "2025-07-05",
                time = "14:00:00Z"
            ),
            raceName = "British Grand Prix",
            round = "12",
            season = "2025",
            secondPractice = SecondPractice(
                date = "2025-07-04",
                time = "15:00:00Z"
            ),
            sprint = Sprint(
                date = "2025-07-05",
                time = "11:00:00Z"
            ),
            thirdPractice = null,
            time = "14:00:00Z",
            url = "https://en.wikipedia.org/wiki/2025_British_Grand_Prix"
        )
    )

}