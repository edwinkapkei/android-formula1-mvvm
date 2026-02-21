package com.edwinkapkei.formula1.data.model.driver

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MRData(
    @SerialName("limit")
    val limit: String,
    @SerialName("offset")
    val offset: String,
    @SerialName("series")
    val series: String,
    @SerialName("StandingsTable")
    val standingsTable: StandingsTable,
    @SerialName("total")
    val total: String,
    @SerialName("url")
    val url: String,
    @SerialName("xmlns")
    val xmlns: String
)
