package com.happymoonday.presentation.feature.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.presentation.R
import com.happymoonday.presentation.databinding.ItemSearchFilterTypeBinding
import com.happymoonday.presentation.feature.search.viewholder.ItemProductionFilterViewHolder

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 제작년도 필터 리스트를 구성하기 위한
 * 리사이클러뷰 어댑터
 *
 * @author LeeDongHun
 *
 *
 **/
class ProductionYearFilterRvAdapter :
    ListAdapter<SearchFilterEntity.ProductionYearFilter, RecyclerView.ViewHolder>(diffUtil) {

    private lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onClickItem(item: SearchFilterEntity.ProductionYearFilter)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemSearchFilterTypeBinding: ItemSearchFilterTypeBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_filter_type,
                parent,
                false
            )
        return ItemProductionFilterViewHolder(itemSearchFilterTypeBinding).apply {
            itemView.setOnClickListener {
                itemClickListener.onClickItem(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemProductionFilterViewHolder).apply {
            bind(currentList[bindingAdapterPosition])
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SearchFilterEntity.ProductionYearFilter>() {
            override fun areItemsTheSame(
                oldItem: SearchFilterEntity.ProductionYearFilter,
                newItem: SearchFilterEntity.ProductionYearFilter
            ): Boolean {
                return oldItem.filterType == newItem.filterType
            }

            override fun areContentsTheSame(
                oldItem: SearchFilterEntity.ProductionYearFilter,
                newItem: SearchFilterEntity.ProductionYearFilter
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
