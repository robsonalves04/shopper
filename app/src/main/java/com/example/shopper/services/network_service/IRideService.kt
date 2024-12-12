package com.example.shopper.services.network_service


import com.example.shopper.models.DetailsDriverHistoric
import com.example.shopper.models.DriverDetails
import com.example.shopper.models.HistoricRidesModel
import com.example.shopper.models.RideConfirmationModel
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.models.RideFinishModel
import com.example.shopper.models.RideRequestModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Url
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


//    //== GET do protocolo HTTP
//    @GET("{cep}/json/")
//    suspend fun findAndress(@Path("cep") cep: String): MarketAndressModel


interface IRideService {

    @POST("ride/estimate")
    suspend fun findRideEstimate(
        @Body request: RideRequestModel
    ): RideEstimateModel


    @PATCH("ride/confirm")
    suspend fun confirmRide(
        @Body request: RideConfirmationModel
    ): RideFinishModel

//    @GET("/ride/{customer_id}?driver_id={driver_id}")
//    suspend fun historicRide(
//        @Body request : DetailsDriverHistoric
//    ): HistoricRidesModel

    @GET("/ride/{customer_id}?driver_id={driver_id}")
    suspend fun historicRide(
        @Path( "customer_id") customer_id: String,
        @Path( "driver_id") driver_id: String,
    ): HistoricRidesModel


}








