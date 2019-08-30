package com.zrz.android.swcurrency.repository.currency

import com.zrz.android.swcurrency.entity.SWCurrency
import io.reactivex.Single

interface CurrencyRepository {

    fun getLatestRates(baseCurrencyCode: String): Single<List<SWCurrency>>

    fun getConverterRate(
        baseCurrencyCode: String,
        secondaryCurrencyCode: String,
        amount: String
    ): Single<String>
}