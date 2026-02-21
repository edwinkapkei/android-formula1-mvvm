package com.edwinkapkei.formula1.views.constructors

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.data.model.constructor.ConstructorAndTeamCarImage
import com.edwinkapkei.formula1.utilities.CustomDateFormatter.getCurrentYear
import com.edwinkapkei.formula1.views.common.ErrorDialog
import com.edwinkapkei.formula1.views.common.LoadingScreen
import com.edwinkapkei.formula1.views.viewmodel.TeamsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsScreen(
    viewModel: TeamsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCurrentTeams(getCurrentYear())
    }

    PullToRefreshBox(
        isRefreshing = uiState.isLoading && uiState.teams.isNotEmpty(),
        onRefresh = { viewModel.getCurrentTeams(getCurrentYear()) },
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.isLoading && uiState.teams.isEmpty()) {
            LoadingScreen()
        } else {
            TeamsList(uiState.teams)
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
fun TeamsList(teams: List<ConstructorAndTeamCarImage>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        itemsIndexed(teams) { index, team ->
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
                            text = team.constructorStanding.constructor.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = stringResource(
                                R.string.points,
                                team.constructorStanding.points
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    AsyncImage(
                        modifier = Modifier.size(80.dp),
                        model = team.teamCarImageUrl,
                        contentDescription = "team car image",
                        placeholder = painterResource(R.drawable.steering_blue)
                    )
                }
                HorizontalDivider(color = Color(0xFFE2E2E2))
            }
        }
    }
}
