package com.edwinkapkei.formula1.data.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("country")
    val country: String,
    @SerialName("lat")
    val lat: String,
    @SerialName("locality")
    val locality: String,
    @SerialName("long")
    val long: String
)
