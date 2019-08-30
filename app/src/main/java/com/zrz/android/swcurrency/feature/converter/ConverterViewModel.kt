package com.zrz.android.swcurrency.feature.converter

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zrz.android.swcurrency.core.base.BaseViewModel
import com.zrz.android.swcurrency.repository.currency.CurrencyRepository

class ConverterViewModel(
    app: Application,
    private val currencyRepository: CurrencyRepository
) : BaseViewModel(app) {

    val converterRateLD: MutableLiveData<String> = MutableLiveData()

    init {
        converterRateLD.value = "0"
    }

    fun requestConverterRate(
        baseCurrencyCode: String,
        secondaryCurrencyCode: String,
        amount: String
    ) {
        doSingle(currencyRepository.getConverterRate(
            baseCurrencyCode,
            secondaryCurrencyCode,
            amount
        )) { converterRateLD.value = it }
    }
}