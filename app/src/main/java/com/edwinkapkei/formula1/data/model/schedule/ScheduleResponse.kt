package com.edwinkapkei.formula1.data.model.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("MRData")
    val mRData: MRData
)
