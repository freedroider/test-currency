package com.zrz.android.swcurrency.repository.currency

import android.net.Uri
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zrz.android.swcurrency.entity.CurrencyResult
import com.zrz.android.swcurrency.entity.Rates
import com.zrz.android.swcurrency.entity.SWCurrency
import com.zrz.android.swcurrency.model.network.NetworkManager
import io.reactivex.Single

class CurrencyRepositoryImpl(private val networkManager: NetworkManager) : CurrencyRepository {

    private val jsonConverter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val ratesAdapter: JsonAdapter<Rates> =
        jsonConverter.adapter<Rates>(Rates::class.java)
    private val resultAdapter: JsonAdapter<CurrencyResult> =
        jsonConverter.adapter<CurrencyResult>(CurrencyResult::class.java)

    override fun getLatestRates(baseCurrencyCode: String): Single<List<SWCurrency>> =
        Single.create { emitter ->
            val latestRatesResponse = networkManager.doGetRequest(
                url = createUrlLatestRates(baseCurrencyCode)
            )
            val ratesMap = ratesAdapter.fromJson(latestRatesResponse)
            emitter.onSuccess(transformRatesToList(ratesMap!!.rates))
        }

    override fun getConverterRate(
        baseCurrencyCode: String,
        secondaryCurrencyCode: String,
        amount: String
    ): Single<String> =
        Single.create { emitter ->
            val converterResponse = networkManager.doGetRequest(
                url = createUrlConverter(baseCurrencyCode, secondaryCurrencyCode, amount)
            )
            val converterResult = resultAdapter.fromJson(converterResponse)
            emitter.onSuccess(converterResult.toString())
        }

    private fun transformRatesToList(ratesMap: Map<String, Double>): List<SWCurrency> {
        val listCurrency = mutableListOf<SWCurrency>()
        ratesMap.forEach { listCurrency.add(SWCurrency(charCode = it.key, rate = it.value)) }
        return listCurrency
    }

    private fun createUrlLatestRates(baseCurrencyCode: String) =
        Uri.parse("$API_URL$API_LATEST")
            .buildUpon()
            .appendQueryParameter(KEY_ACCESS, API_KEY)
            .appendQueryParameter(KEY_BASE, baseCurrencyCode)
            .build()
            .toString()

    private fun createUrlConverter(
        baseCurrencyCode: String,
        secondaryCurrencyCode: String,
        amount: String) =
        Uri.parse("$API_URL$API_CONVERT")
            .buildUpon()
            .appendQueryParameter(KEY_ACCESS, API_KEY)
            .appendQueryParameter(KEY_FROM, baseCurrencyCode)
            .appendQueryParameter(KEY_TO, secondaryCurrencyCode)
            .appendQueryParameter(KEY_AMOUNT, amount)
            .build()
            .toString()

    companion object {
        const val API_KEY = "75b0220fe8dfd1ac9bd398d89e65f599"
        const val API_URL = "http://data.fixer.io/api/"
        const val API_LATEST = "latest"
        const val API_CONVERT = "convert"
        const val KEY_ACCESS = "access_key"
        const val KEY_BASE = "base"
        const val KEY_FROM = "from"
        const val KEY_TO = "to"
        const val KEY_AMOUNT = "amount"
    }
}