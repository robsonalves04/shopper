package com.example.shopper.services.network_service

import com.example.shopper.models.DetailsDriverHistoric
import com.example.shopper.models.DriverDetails
import com.example.shopper.models.HistoricRidesModel

import com.example.shopper.models.RideConfirmationModel
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.models.RideFinishModel
import com.example.shopper.models.RideRequestModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Path


class RideService : IRideService {
    private val apiService: IRideService

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/")
            .client(client)
            .build()

        apiService = retrofit.create(IRideService::class.java)
    }

    // Metodo que faz o POST
    override suspend fun findRideEstimate(@Body request: RideRequestModel): RideEstimateModel {
        // Chama o metodo da API passando o objeto
        return apiService.findRideEstimate(request)
    }

    override suspend fun confirmRide(@Body request: RideConfirmationModel): RideFinishModel {
        // O objeto `request` já é passado como parâmetro, não há necessidade de recriá-lo
        return apiService.confirmRide(request)
    }

    override suspend fun historicRide(customer_id: String, driver_id: String): HistoricRidesModel {
        return apiService.historicRide(customer_id , driver_id)
    }
}
