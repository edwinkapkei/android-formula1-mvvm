package com.edwinkapkei.formula1.data.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Circuit(
    @SerialName("circuitId")
    val circuitId: String,
    @SerialName("circuitName")
    val circuitName: String,
    @SerialName("Location")
    val location: Location,
    @SerialName("url")
    val url: String
)
