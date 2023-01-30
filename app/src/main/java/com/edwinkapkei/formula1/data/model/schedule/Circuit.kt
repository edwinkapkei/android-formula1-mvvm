package com.edwinkapkei.formula1.data.model.schedule


import com.google.gson.annotations.SerializedName

data class Circuit(
    @SerializedName("circuitId")
    val circuitId: String,
    @SerializedName("circuitName")
    val circuitName: String,
    @SerializedName("Location")
    val location: Location,
    @SerializedName("url")
    val url: String
)