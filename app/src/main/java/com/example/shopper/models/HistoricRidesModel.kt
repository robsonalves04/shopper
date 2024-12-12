package com.example.shopper.models

import java.sql.Date


data class HistoricRidesModel(
    val customer_id: String,
    val rides: RidesDetails,
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

data class DetailsDriverHistoric(
    val customer_id: String,
    val driver_id: String,
)