package com.zrz.android.swcurrency.core.di

import android.content.Context
import com.zrz.android.swcurrency.feature.converter.ConverterViewModel
import com.zrz.android.swcurrency.feature.rate.RateViewModel
import com.zrz.android.swcurrency.model.network.NetworkManager
import com.zrz.android.swcurrency.model.network.NetworkManagerImpl
import com.zrz.android.swcurrency.repository.currency.CurrencyRepository
import com.zrz.android.swcurrency.repository.currency.CurrencyRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object AppDI {
    private val managerModule by lazy {
        module { single<NetworkManager> { NetworkManagerImpl() } }
    }
    private val repositoryModule by lazy {
        module { factory<CurrencyRepository> { CurrencyRepositoryImpl(get()) } }
    }
    private val viewModelModule by lazy {
        module { viewModel { RateViewModel(androidApplication(), get()) }
            viewModel { ConverterViewModel(androidApplication(), get()) }
        }
    }

    fun setup(appContext: Context) {
        startKoin {
            androidContext(appContext)
            modules(
                listOf(
                    managerModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}