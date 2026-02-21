package com.edwinkapkei.formula1.data.model.driver

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    @SerialName("code")
    val code: String? = null,
    @SerialName("dateOfBirth")
    val dateOfBirth: String,
    @SerialName("driverId")
    val driverId: String,
    @SerialName("familyName")
    val familyName: String,
    @SerialName("givenName")
    val givenName: String,
    @SerialName("nationality")
    val nationality: String,
    @SerialName("permanentNumber")
    val permanentNumber: String? = null,
    @SerialName("url")
    val url: String
)
