package com.example.shopper.services.network_service

import com.example.shopper.models.CustomerModel
import com.example.shopper.models.RideConfirmationModel
import com.example.shopper.models.RideEstimateModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

//class RideService : IRideService {
//    private val apiService: IRideService
//
//    init {
//        val client = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .build()
//        //== Base URL da requisição
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(MoshiConverterFactory.create())
//            .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws")
//        .client(client)
//            .build()
//
//        apiService = retrofit.create(IRideService::class.java)
//    }
//
//    // Método que faz o POST
//    override suspend fun tokenProdutos(): CustomerModel {
//        return apiService.tokenProdutos()
//
//    }
//
//}

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
    override suspend fun findRideEstimate(
        customerId: String,
        origin: String,
        destination: String
    ): RideEstimateModel {
        return apiService.findRideEstimate(customerId, origin, destination)
    }

    override suspend fun confirmRide(distance: Number, driverId: Number): RideConfirmationModel {
        return apiService.confirmRide(distance, driverId)
    }


}


//    //== Retorno da requisição
//    override suspend fun findAndress(cep: String): CustomerModel {
//        return apiService.findAndress(cep)
//    }