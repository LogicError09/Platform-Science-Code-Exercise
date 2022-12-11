package com.example.platformscienceexercise.model

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryData(
    val drivers: List<String>? = null,
    val shipments: List<String>? = null
)
