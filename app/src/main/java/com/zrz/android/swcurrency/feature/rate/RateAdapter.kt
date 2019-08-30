package com.zrz.android.swcurrency.feature.rate

import android.view.View
import android.view.ViewGroup
import com.zrz.android.swcurrency.R
import com.zrz.android.swcurrency.core.base.BaseAdapter
import com.zrz.android.swcurrency.entity.SWCurrency
import com.zrz.android.swcurrency.util.extension.inflateView
import kotlinx.android.synthetic.main.item_rate.view.*

class RateAdapter : BaseAdapter<SWCurrency, RateAdapter.RateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RateViewHolder(parent.inflateView(R.layout.item_rate))

    override fun compareItemsById(): (Pair<SWCurrency, SWCurrency>) -> Boolean =
        { messagePair -> messagePair.first.charCode == messagePair.second.charCode }

    class RateViewHolder(view: View) : BaseViewHolder<SWCurrency>(view) {
        override fun onBind(currency: SWCurrency) {
            itemView.ivCurrencyFlag.setImageResource(R.mipmap.un_flag)
            itemView.tvCurrencyCharCode.text = currency.charCode
            itemView.tvCurrencyRate.text = currency.rate.toString()
        }
    }
}