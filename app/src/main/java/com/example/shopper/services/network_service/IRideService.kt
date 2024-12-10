package com.example.shopper.services.network_service


import com.example.shopper.models.CustomerModel
import com.example.shopper.models.RideConfirmationModel
import com.example.shopper.models.RideEstimateModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PATCH
import retrofit2.http.POST


//    //== GET do protocolo HTTP
//    @GET("{cep}/json/")
//    suspend fun findAndress(@Path("cep") cep: String): MarketAndressModel


interface IRideService {

    @FormUrlEncoded
    @POST("ride/estimate")
    suspend fun findRideEstimate(
        @Field("customer_id") customerId: String,
        @Field("origin") origin: String,
        @Field("destination") destination: String
    ): RideEstimateModel

    @FormUrlEncoded
    @PATCH("ride/confirm")
    suspend fun confirmRide(
        @Field("distance") distance: Number,
        @Field("driver_id") driverId: Number
    ): RideConfirmationModel

}











