package com.example.shopper.models

data class RideConfirmationModel(
    val customer_id: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: DriverDetails,
    val value: Double
)

data class DriverDetails(
    val id: Int,
    val name: String
)

