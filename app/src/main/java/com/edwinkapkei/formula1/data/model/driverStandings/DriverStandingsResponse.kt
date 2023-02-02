package com.edwinkapkei.formula1.data.model.driverStandings


import com.google.gson.annotations.SerializedName

data class DriverStandingsResponse(
    @SerializedName("MRData")
    val mRData: MRData
)