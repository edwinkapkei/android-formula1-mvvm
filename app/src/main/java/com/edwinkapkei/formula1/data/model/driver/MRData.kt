package com.edwinkapkei.formula1.data.model.driver

import com.google.gson.annotations.SerializedName

data class MRData(
    @SerializedName("limit")
    val limit: String,
    @SerializedName("offset")
    val offset: String,
    @SerializedName("series")
    val series: String,
    @SerializedName("StandingsTable")
    val standingsTable: StandingsTable,
    @SerializedName("total")
    val total: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("xmlns")
    val xmlns: String,
)
