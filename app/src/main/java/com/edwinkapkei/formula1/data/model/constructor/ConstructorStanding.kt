package com.edwinkapkei.formula1.data.model.constructor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorStanding(
    @SerialName("Constructor")
    val constructor: Constructor,
    @SerialName("points")
    val points: String,
    @SerialName("position")
    val position: String,
    @SerialName("positionText")
    val positionText: String,
    @SerialName("wins")
    val wins: String
)
