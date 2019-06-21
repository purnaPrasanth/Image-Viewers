package com.purna.baseandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

abstract class SingleTypeBaseRvAdapter<BINDING : ViewDataBinding, DATA>(val mContext: Context, @LayoutRes val layoutId: Int) :
    RecyclerView.Adapter<BaseHolder<BINDING>>() {
    protected val TAG: String = javaClass.simpleName
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    var onItemLongClickListener: AdapterView.OnItemLongClickListener? = null

    private val asyncDiffer by lazy { AsyncListDiffer(this, diffCallback) }

    private val diffCallback = object : DiffUtil.ItemCallback<DATA>() {
        override fun areItemsTheSame(oldItem: DATA, newItem: DATA) = areItemsSame(oldItem, newItem)

        override fun areContentsTheSame(oldItem: DATA, newItem: DATA) = areContentsSame(oldItem, newItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<BINDING> {
        val view =
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder<BINDING>, position: Int) {
        onBindViewHolder(holder.binding(), position)
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener { v ->
                onItemClickListener?.onItemClick(
                    null,
                    v,
                    holder.adapterPosition,
                    holder.itemId
                )
            }
        }

        holder.itemView.setOnLongClickListener { v ->
            onItemLongClickListener?.onItemLongClick(
                null,
                v,
                holder.adapterPosition,
                holder.itemId
            ) == false
        }
    }

    fun setData(data: List<DATA>) {
        asyncDiffer.submitList(data)
    }

    fun getItem(position: Int) = asyncDiffer.currentList[position]

    fun getItems(): List<DATA> = asyncDiffer.currentList

    override fun getItemCount() = asyncDiffer.currentList.size

    protected abstract fun onBindViewHolder(binding: BINDING, position: Int)
    protected abstract fun areItemsSame(oldItem: DATA, newItem: DATA): Boolean
    protected abstract fun areContentsSame(oldItem: DATA, newItem: DATA): Boolean
}

class BaseHolder<out T : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: T = DataBindingUtil.bind(itemView)!!

    fun binding(): T {
        return binding
    }
}