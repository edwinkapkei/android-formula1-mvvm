package com.edwinkapkei.formula1.data.model.schedule


import com.google.gson.annotations.SerializedName

data class RaceTable(
    @SerializedName("Races")
    val races: List<Race>,
    @SerializedName("season")
    val season: String
)