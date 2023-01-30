package com.edwinkapkei.formula1.data.model.schedule


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("MRData")
    val mRData: MRData
)