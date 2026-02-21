package com.edwinkapkei.formula1.data.model.constructor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsLists(
    @SerialName("ConstructorStandings")
    val constructorStandings: List<ConstructorStanding>,
    @SerialName("round")
    val round: String,
    @SerialName("season")
    val season: String
)
