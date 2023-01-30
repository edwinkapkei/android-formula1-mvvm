package com.edwinkapkei.formula1.data.model.drivers


import com.google.gson.annotations.SerializedName

data class DriverTable(
    @SerializedName("Drivers")
    val drivers: List<Driver>,
    @SerializedName("season")
    val season: String
)