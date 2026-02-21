package com.edwinkapkei.formula1.data.model.driver

import com.google.gson.annotations.SerializedName

data class DriversResponse(
    @SerializedName("MRData")
    val mRData: MRData
)
