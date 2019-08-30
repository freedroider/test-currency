package com.zrz.android.swcurrency.util.extension

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

inline fun <T> RecyclerView.Adapter<*>.addItemDiff(
    oldList: List<T>, newList: List<T>,
    crossinline compare: (Pair<T, T>) -> Boolean
) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            compare(oldList[oldItemPosition] to newList[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size
    })

    diff.dispatchUpdatesTo(this)
}