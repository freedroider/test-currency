package com.zrz.android.swcurrency.core.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zrz.android.swcurrency.util.extension.addItemDiff

abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder<T>>(
    val rates: MutableList<T> = mutableListOf(),
    var itemClickAction: ((Int, View) -> Unit)? = null
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount() = rates.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (isInPositionsRange(position)) {
            holder.onBind(rates[position])
            holder.itemView.setOnClickListener{ view ->
                if (isInPositionsRange(holder.adapterPosition)) {
                    itemClickAction?.invoke(position, view)
                }
            }
        }
    }

    fun updateRate(ratesLD: List<T>) {
        if (ratesLD.size > rates.size) {
            addItemDiff(rates, ratesLD, compareItemsById())
            addItem(ratesLD)
        }
    }

    open fun addItem(newRates: List<T>) {
        val oldLastIndex = rates.lastIndex
        newRates.forEachIndexed { index, item -> if (index > oldLastIndex) rates.add(item) }
    }

    private fun isInPositionsRange(position: Int) =
        position != RecyclerView.NO_POSITION && position < itemCount

    abstract fun compareItemsById(): (Pair<T, T>) -> Boolean

    abstract class BaseViewHolder<M>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(currency: M)
    }
}