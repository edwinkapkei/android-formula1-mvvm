package com.edwinkapkei.formula1.data.model.driver

import com.edwinkapkei.formula1.data.model.constructor.Constructor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverStanding(
    @SerialName("Constructors")
    val constructors: List<Constructor>,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("points")
    val points: String,
    @SerialName("position")
    val position: String,
    @SerialName("positionText")
    val positionText: String,
    @SerialName("wins")
    val wins: String
)
