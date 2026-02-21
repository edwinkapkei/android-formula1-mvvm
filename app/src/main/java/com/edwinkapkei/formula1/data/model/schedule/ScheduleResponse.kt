package com.edwinkapkei.formula1.data.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    @SerialName("MRData")
    val mRData: MRData
)
