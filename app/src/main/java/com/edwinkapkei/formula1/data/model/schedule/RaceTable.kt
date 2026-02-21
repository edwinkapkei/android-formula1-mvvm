package com.edwinkapkei.formula1.data.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceTable(
    @SerialName("Races")
    val races: List<Race>,
    @SerialName("season")
    val season: String
)
