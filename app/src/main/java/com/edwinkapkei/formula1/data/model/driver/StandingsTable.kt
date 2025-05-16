package com.edwinkapkei.formula1.data.model.driver

import com.google.gson.annotations.SerializedName

data class StandingsTable(
    @SerializedName("season")
    val season: String,
    @SerializedName("StandingsLists")
    val standingsLists: List<StandingsLists>,
)
