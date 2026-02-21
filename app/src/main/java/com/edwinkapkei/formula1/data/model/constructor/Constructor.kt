package com.edwinkapkei.formula1.data.model.constructor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Constructor(
    @SerialName("constructorId")
    val constructorId: String,
    @SerialName("name")
    val name: String,
    @SerialName("nationality")
    val nationality: String,
    @SerialName("url")
    val url: String
)
