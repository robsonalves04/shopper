package com.example.shopper.models

import java.sql.Date
import java.time.LocalDateTime


data class HistoricRidesModel(
    val customer_id: String,
    val rides: List<RidesDetails>?,
    val destination: String
)

data class RidesDetails(
    val id: Number,
    val date: Date,
    val origin: String,
    val destination: String,
    val distance: String,
    val duration: String,
    val driver: DriverHistoricRides,
    val value: Number,
)

data class DriverHistoricRides(
    val id: Number,
    val name: String,
)

