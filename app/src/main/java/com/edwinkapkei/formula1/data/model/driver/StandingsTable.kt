package com.edwinkapkei.formula1.data.model.driver

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsTable(
    @SerialName("season")
    val season: String,
    @SerialName("StandingsLists")
    val standingsLists: List<StandingsLists>
)
