package com.edwinkapkei.formula1.data.model.constructor


import com.google.gson.annotations.SerializedName

data class ConstructorsResponse(
    @SerializedName("MRData")
    val mRData: MRData
)