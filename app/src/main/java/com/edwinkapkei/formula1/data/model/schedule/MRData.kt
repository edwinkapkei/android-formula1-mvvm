package com.edwinkapkei.formula1.data.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MRData(
    @SerialName("limit")
    val limit: String,
    @SerialName("offset")
    val offset: String,
    @SerialName("RaceTable")
    val raceTable: RaceTable,
    @SerialName("series")
    val series: String,
    @SerialName("total")
    val total: String,
    @SerialName("url")
    val url: String,
    @SerialName("xmlns")
    val xmlns: String
)
