package com.example.platformscienceexercise.repository

import android.app.Application
import com.example.platformscienceexercise.model.DeliveryData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class DeliveryDataRepository(application: Application) {

    private val jsonDataInString: String =
        application.assets.open("data.json").bufferedReader().use { it.readText() }

    val deliveryData = Json.decodeFromString<DeliveryData>(jsonDataInString)
}