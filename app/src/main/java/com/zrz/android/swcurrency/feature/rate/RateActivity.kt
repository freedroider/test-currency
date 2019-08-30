package com.zrz.android.swcurrency.feature.rate

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zrz.android.swcurrency.R
import com.zrz.android.swcurrency.core.base.BaseActivity
import com.zrz.android.swcurrency.entity.SWCurrency
import com.zrz.android.swcurrency.feature.converter.ConverterActivity
import com.zrz.android.swcurrency.util.extension.startNewActivity
import kotlinx.android.synthetic.main.activity_rate.*
import kotlinx.android.synthetic.main.layout_rate_app_bar.*
import org.koin.android.ext.android.inject

class RateActivity : BaseActivity() {

    private val rateViewModel: RateViewModel by inject()

    private lateinit var rateAdapter: RateAdapter

    override fun obtainLayoutResID(): Int = R.layout.activity_rate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerViewInitializing()
        observeLiveData()
        tvBaseCurrencyCharCode.text = BASE_CURRENCY_CODE
        requestLatestRates()
    }

    private fun requestLatestRates(){
        rateViewModel.requestLatestRates(BASE_CURRENCY_CODE)
    }

    private fun onItemClick(baseCurrency: SWCurrency, selectedCurrency: SWCurrency) {
        startNewActivity<ConverterActivity>(
            KEY_CONVERTER,
            KEY_BASE_CURRENCY to baseCurrency,
            KEY_SELECTED_CURRENCY to selectedCurrency
            )
    }

    private fun recyclerViewInitializing() {
        val chatLayoutManager = LinearLayoutManager(applicationContext)
        chatLayoutManager.stackFromEnd
        rateAdapter = RateAdapter()
        rateAdapter.itemClickAction = { position, _ ->
            onItemClick(
                rateAdapter.rates[position],
                SWCurrency(BASE_CURRENCY_CODE, 1.00)
            )
        }
        rvRates.apply {
            layoutManager = chatLayoutManager
            adapter = rateAdapter
        }
    }

    private fun observeLiveData(){
        observingAction(rateViewModel.throwableLD, { handleError(it) })
        observingAction(rateViewModel.ratesLD, { rateAdapter.updateRate(it) })
    }

    companion object {
        const val BASE_CURRENCY_CODE = "EUR"
        const val KEY_CONVERTER = "key_converter"
        const val KEY_BASE_CURRENCY = "key_base_currency"
        const val KEY_SELECTED_CURRENCY = "key_selected_currency"
        const val BUNDLE_KEY = "key_bundle"
        const val INTENT_KEY = "key_intent"
    }
}
