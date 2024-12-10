package com.example.shopper.models
//== Modelo de requisição da API
data class RideEstimateModel(
    val origin: Origin? = null,
    val destination: Destination? = null,
    val distance: Number? = null,
    val duration: String? = null,
    val options: List<Options> ? = null,
    val driver: Driver? = null,
    val value: Number? = null
)
data class Driver(
    val id: Number? = null,
    val name: String? = null
)

data class Origin(
    val latitude: Double? = null,
    val longitude: Double? = null
)

data class Destination(
    val latitude: Double? = null,
    val longitude: Double? = null
)

data class Options(
    val id: Number? = null,
    val name: String? = null,
    val description: String? = null,
    val vehicle: String? = null,
    val value: Double? = null
)



