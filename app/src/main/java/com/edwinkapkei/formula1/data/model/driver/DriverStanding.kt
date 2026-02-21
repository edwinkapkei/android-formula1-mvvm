package com.edwinkapkei.formula1.data.model.driver

import com.edwinkapkei.formula1.data.model.constructor.Constructor
import com.google.gson.annotations.SerializedName

data class DriverStanding(
    @SerializedName("Constructors")
    val constructors: List<Constructor>,
    @SerializedName("Driver")
    val driver: Driver,
    @SerializedName("points")
    val points: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("positionText")
    val positionText: String,
    @SerializedName("wins")
    val wins: String
)
