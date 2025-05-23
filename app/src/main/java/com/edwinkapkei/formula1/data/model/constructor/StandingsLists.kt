package com.edwinkapkei.formula1.data.model.constructor

import com.google.gson.annotations.SerializedName

data class StandingsLists(
    @SerializedName("ConstructorStandings")
    val constructorStandings: List<ConstructorStanding>,
    @SerializedName("round")
    val round: String,
    @SerializedName("season")
    val season: String,
)
