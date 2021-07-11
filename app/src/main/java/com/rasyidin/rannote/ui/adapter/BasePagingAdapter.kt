package com.rasyidin.rannote.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingAdapter<T: Any>(
    private val layoutId: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BasePagingAdapter<T>.PagingViewHolder>(diffUtil) {

    inner class PagingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var onItemClickListener: ((T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

}