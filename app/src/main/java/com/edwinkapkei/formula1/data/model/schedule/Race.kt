package com.edwinkapkei.formula1.data.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Race(
    @SerialName("Circuit")
    val circuit: Circuit,
    @SerialName("date")
    val date: String,
    @SerialName("FirstPractice")
    val firstPractice: FirstPractice? = null,
    @SerialName("Qualifying")
    val qualifying: Qualifying? = null,
    @SerialName("raceName")
    val raceName: String,
    @SerialName("round")
    val round: String,
    @SerialName("season")
    val season: String,
    @SerialName("SecondPractice")
    val secondPractice: SecondPractice? = null,
    @SerialName("Sprint")
    val sprint: Sprint? = null,
    @SerialName("ThirdPractice")
    val thirdPractice: ThirdPractice? = null,
    @SerialName("time")
    val time: String? = null,
    @SerialName("url")
    val url: String
)
