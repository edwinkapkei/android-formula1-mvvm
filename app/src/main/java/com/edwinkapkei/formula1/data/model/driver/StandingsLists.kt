package com.edwinkapkei.formula1.data.model.driver

import com.google.gson.annotations.SerializedName

data class StandingsLists(
    @SerializedName("DriverStandings")
    val driverStandings: List<DriverStanding>,
    @SerializedName("round")
    val round: String,
    @SerializedName("season")
    val season: String,
)
