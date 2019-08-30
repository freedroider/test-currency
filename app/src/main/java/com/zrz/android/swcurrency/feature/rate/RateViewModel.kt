package com.zrz.android.swcurrency.feature.rate

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zrz.android.swcurrency.core.base.BaseViewModel
import com.zrz.android.swcurrency.entity.SWCurrency
import com.zrz.android.swcurrency.repository.currency.CurrencyRepository

class RateViewModel(
    app: Application,
    private val currencyRepository: CurrencyRepository
    ) : BaseViewModel(app) {

    val ratesLD: MutableLiveData<List<SWCurrency>> = MutableLiveData()

    init {
        ratesLD.value = mutableListOf()
    }

    fun requestLatestRates(baseCurrencyCode: String) {
        doSingle(currencyRepository.getLatestRates(baseCurrencyCode)) { ratesLD.value = it }
    }
}