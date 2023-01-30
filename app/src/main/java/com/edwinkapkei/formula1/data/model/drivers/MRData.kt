package com.edwinkapkei.formula1.data.model.drivers


import com.google.gson.annotations.SerializedName

data class MRData(
    @SerializedName("DriverTable")
    val driverTable: DriverTable,
    @SerializedName("limit")
    val limit: String,
    @SerializedName("offset")
    val offset: String,
    @SerializedName("series")
    val series: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("xmlns")
    val xmlns: String
)