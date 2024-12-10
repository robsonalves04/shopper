package com.example.shopper.starting


import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.shopper.services.network_service.IRideService
import com.example.shopper.services.network_service.RideService
import com.example.shopper.viewmodel.RideViewModel
import com.example.shopper.viewmodel.ShopperViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Starting : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        //==Inicialização pelo Koin
        startKoin {
            androidLogger()
            androidContext(this@Starting)

            modules(module {
                //== Injeção de dependencia do Service
//                single<IMarketProdutoService> { MarketProdutoService() }
                single <IRideService> { RideService() }

                //==Injeção do ViewModel
//                viewModel { MarketProdutoViewModel(get(), get()) }

                viewModel { ShopperViewModel()}
                viewModel { RideViewModel(get()) }
            })
        }
        //== Rodapé padrão do celular
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    //== Variavel que faz com que a instacia inicie depois
    companion object {
        lateinit var instance: Starting
            private set
    }
}



