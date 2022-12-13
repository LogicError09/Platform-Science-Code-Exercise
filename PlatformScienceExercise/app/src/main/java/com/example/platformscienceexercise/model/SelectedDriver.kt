package com.example.platformscienceexercise.model

import kotlinx.serialization.Serializable

@Serializable
data class SelectedDriver(
    val driver: String? = null,
    val shipment:String? = null
): java.io.Serializable
