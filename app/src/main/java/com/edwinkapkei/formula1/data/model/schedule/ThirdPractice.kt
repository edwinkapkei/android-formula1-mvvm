package com.edwinkapkei.formula1.data.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThirdPractice(
    @SerialName("date")
    val date: String,
    @SerialName("time")
    val time: String
)
