package com.edwinkapkei.formula1.data.model.driver

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsLists(
    @SerialName("DriverStandings")
    val driverStandings: List<DriverStanding>,
    @SerialName("round")
    val round: String,
    @SerialName("season")
    val season: String
)
