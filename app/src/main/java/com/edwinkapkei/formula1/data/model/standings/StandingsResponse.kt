package com.edwinkapkei.formula1.data.model.standings


import com.google.gson.annotations.SerializedName

data class StandingsResponse(
    @SerializedName("MRData")
    val mRData: MRData
)