package com.edwinkapkei.formula1.data.model.schedule

import com.google.gson.annotations.SerializedName

data class SecondPractice(
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String,
)
