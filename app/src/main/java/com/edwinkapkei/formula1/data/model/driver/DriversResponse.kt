package com.edwinkapkei.formula1.data.model.driver

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriversResponse(
    @SerialName("MRData")
    val mRData: MRData
)
