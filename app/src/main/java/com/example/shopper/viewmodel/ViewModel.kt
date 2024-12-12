package com.example.shopper.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.componets.toastSnackbar
import com.example.shopper.models.DetailsDriverHistoric
import com.example.shopper.models.DriverDetails
import com.example.shopper.models.HistoricRidesModel
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.models.RideConfirmationModel
import com.example.shopper.models.RideFinishModel
import com.example.shopper.models.RideRequestModel
import com.example.shopper.services.network_service.IRideService
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class ShopperViewModel : ViewModel() {

    var userId by mutableStateOf("")

    //        private set
    var userPassword by mutableStateOf("")
        private set

    fun setUserData(id: String, password: String) {
        userId = id
        userPassword = password
    }

}

class RideViewModel(private val rideService: IRideService) : ViewModel() {
    private val _rideEstimate = mutableStateOf<List<RideEstimateModel?>>(emptyList())
    val rideEstimate: State<List<RideEstimateModel?>> get() = _rideEstimate

    private val _rideHitoric = mutableStateOf<List<HistoricRidesModel?>>(emptyList())
    val rideHitoric: State<List<HistoricRidesModel?>> get() = _rideHitoric

    val motorista_api = MutableLiveData<List<RideEstimateModel>>()

    private val _rideConfirmation = mutableStateOf<RideConfirmationModel?>(null)
    val rideConfirmation: State<RideConfirmationModel?> get() = _rideConfirmation

    fun fetchRideEstimate(
        context: Context,
        customerId: String,
        origin: String,
        destination: String
    ) {
        viewModelScope.launch {
            try {
                val request = RideRequestModel(
                    customer_id = customerId,
                    origin = origin,
                    destination = destination
                )
                // Chame o metodo da API com o objeto
                val estimate = rideService.findRideEstimate(request)

                // Atualize o estado com o resultado
                _rideEstimate.value = listOf(estimate)
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> toastSnackbar(
                        context,
                        "Os dados fornecidos são inválidos! Verifique e tente novamente."
                    )
                }
            } catch (e: IOException) {
                toastSnackbar(context, "Erro de rede. Verifique sua conexão com a internet.")
            } catch (e: Exception) {
                toastSnackbar(context, "Erro inesperado: ${e.message}")
            }
            // Log de erro
            Log.e("RideViewModel", "Error fetching ride estimate")
        }
    }


    fun confirmRide(
        context: Context,
        customerId: String,
        origin: String,
        destination: String,
        distance: Number,
        duration: String,
        driver: DriverDetails,
        value: Number,
        onSuccess: (RideFinishModel) -> Unit,
//        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = RideConfirmationModel(
                    customer_id = customerId,
                    origin = origin,
                    destination = destination,
                    distance = distance,
                    duration = duration,
                    driver = driver,
                    value = value.toDouble()
                )
                val response = rideService.confirmRide(request)
                // Execute o callback de sucesso
                onSuccess(response)
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> toastSnackbar(
                        context,
                        "Os dados fornecidos são inválidos! Verifique e tente novamente."
                    )

                    404 -> toastSnackbar(context, "Motorista não encontrado.")
                    406 -> toastSnackbar(context, "Quilometragem inválida para o motorista.")
                    else -> toastSnackbar(context, "Erro inesperado: ${e.message}")
                }
            } catch (e: IOException) {
                toastSnackbar(context, "Erro de rede. Verifique sua conexão com a internet.")
            } catch (e: Exception) {
                toastSnackbar(context, "Erro inesperado: ${e.message}")
            }
        }
    }


    fun historicRides(context: Context, customerId: String, driver_id: String, ) {
        viewModelScope.launch {
            try {
                // Chame o metodo da API com o objeto
                val historico = rideService.historicRide(customerId, driver_id)

                // Atualize o estado com o resultado
                _rideHitoric.value = listOf(historico)
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> toastSnackbar(
                        context,
                        "Os dados fornecidos são inválidos! Verifique e tente novamente."
                    )
                }
            } catch (e: IOException) {
                toastSnackbar(context, "Erro de rede. Verifique sua conexão com a internet.")
            } catch (e: Exception) {
                toastSnackbar(context, "Erro inesperado: ${e.message}")
            }
            // Log de erro
            Log.e("RideViewModel", "Error fetching ride estimate")
        }
    }
}


//class RideViewModel(
//    private val rideService: IRideService
//) : ViewModel() {
//
////    private val _rideData = MutableLiveData<CustomerModel>()
////    val rideData: LiveData<CustomerModel> get() = _rideData
//
//    val _rideData = MutableLiveData<CustomerModel>()
//
//    val _rideEstimate = MutableLiveData <RideEstimateModel>()
//
//
//    fun fetchRideEstimate(customerId: String, origin: String, destination: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = rideService.findRideEstimate(customerId, origin, destination)
//                    _rideEstimate.value = response
//
//
////                    toastSnackbar(context = context, "Deu Certo")
//
//            } catch (e: Exception) {
//                Log.e("RideViewModel", "Erro ao buscar estimativa de rota", e)
//            }
//
//        }
//    }


//    fun responseRideEstimate(context: Context) {
//        viewModelScope.launch(Dispatchers.Main) {
//            val callback = Callback<List<RideEstimateModel>>(
//                onSucesso = { model ->
//                    sucessMock(model)
//                },
//            )
//
//        }
//    }

//    fun sucessMock(model: List <RideEstimateModel>) {
//        _rideEstimate.value = model
//
//    }


//== Classe do callback com o tratamento da falha ou sucesso, que irá apresentar na tela
data class Callback<D>(
    val onSucesso: (res: D) -> Unit,
)