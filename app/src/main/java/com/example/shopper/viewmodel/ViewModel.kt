package com.example.shopper.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.models.CustomerModel
import com.example.shopper.models.RideConfirmationModel
import com.example.shopper.services.network_service.IRideService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    val motorista_api = MutableLiveData<List<RideEstimateModel>>()

    private val _rideConfirmation = mutableStateOf<RideConfirmationModel?>(null)
    val rideConfirmation: State<RideConfirmationModel?> get() = _rideConfirmation

    fun fetchRideEstimate(customerId: String, origin: String, destination: String) {
        viewModelScope.launch {
            try {
                val estimate = rideService.findRideEstimate(customerId, origin, destination)
                _rideEstimate.value = listOf(estimate)
            } catch (e: Exception) {
                Log.e("RideViewModel", "Error fetching ride estimate", e)
            }
        }
    }

    fun confirmRide(distance: Number, driverId: Number) {
        viewModelScope.launch {
            try {
                val response = rideService.confirmRide(distance, driverId,)
                _rideConfirmation.value = response
            } catch (e: Exception) {
                Log.e("RideViewModel", "Error confirming ride", e)
                _rideConfirmation.value = null
            }
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