package com.edwinkapkei.formula1.data.model.constructor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorsResponse(
    @SerialName("MRData")
    val mRData: MRData
)
