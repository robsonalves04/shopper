package com.example.shopper.services.network_service



import com.example.shopper.models.HistoricRidesModel
import com.example.shopper.models.RideConfirmationModel
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.models.RideFinishModel
import com.example.shopper.models.RideRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface IRideService {

    @POST("ride/estimate")
    suspend fun findRideEstimate(
        @Body request: RideRequestModel
    ): RideEstimateModel


    @PATCH("ride/confirm")
    suspend fun confirmRide(
        @Body request: RideConfirmationModel
    ): RideFinishModel

    @GET("/ride/{customer_id}")
    suspend fun historicRide(
        @Path("customer_id") customerId: String,
        @Query("driver_id") driverId: String,
    ): List<HistoricRidesModel>

}