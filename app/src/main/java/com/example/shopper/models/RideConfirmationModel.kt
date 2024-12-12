package com.example.shopper.models

data class RideConfirmationModel(
    val customer_id: String,
    val origin: String,
    val destination: String,
    val distance: Number,
    val duration: String,
    val driver:  DriverDetails,
    val value: Number
)

data class DriverDetails(
    val id: Number,
    val name: String
)

data class RideFinishModel(
    val success: Boolean,
    val message: String,
)



