package com.happymoonday.presentation.feature.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.presentation.R
import com.happymoonday.presentation.databinding.ItemSearchFilterTypeWithSelectorBinding
import com.happymoonday.presentation.feature.search.viewholder.ItemArtCategoryFilterViewHolder

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 예술 카테고리 필터 리스트를 구성하기 위한
 * 리사이클러뷰 어댑터
 *
 * @author LeeDongHun
 *
 *
 **/
class ArtCategoryFilterRvAdapter :
    ListAdapter<SearchFilterEntity.ArtCategoryFilter, RecyclerView.ViewHolder>(diffUtil) {
    private lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onCheckBoxClicked(item: SearchFilterEntity.ArtCategoryFilter,isChecked:Boolean)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemSearchFilterTypeWithSelectorBinding: ItemSearchFilterTypeWithSelectorBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_filter_type_with_selector,
                parent,
                false
            )
        return ItemArtCategoryFilterViewHolder(itemSearchFilterTypeWithSelectorBinding).apply {

            //text뷰 눌러도 check 변경되게 수정
            binding.tvFilterTitle.setOnClickListener {
                binding.cbFilterCheck.isChecked = !binding.cbFilterCheck.isChecked
            }
            binding.cbFilterCheck.setOnCheckedChangeListener { _, isChecked ->
                binding.cbFilterCheck.isChecked = isChecked
                binding.tvFilterTitle.apply {
                    val textColor = itemView.resources.getColor(
                        if (isChecked) {
                            R.color.black
                        } else {
                            R.color.gray
                        }, null
                    )
                    setTextColor(textColor)
                }
                itemClickListener.onCheckBoxClicked(currentList[bindingAdapterPosition],isChecked)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemArtCategoryFilterViewHolder).apply {
            bind(currentList[bindingAdapterPosition])
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SearchFilterEntity.ArtCategoryFilter>() {
            override fun areItemsTheSame(
                oldItem: SearchFilterEntity.ArtCategoryFilter,
                newItem: SearchFilterEntity.ArtCategoryFilter
            ): Boolean {
                return oldItem.filterType == newItem.filterType && oldItem.isSelected == newItem.isSelected
            }

            override fun areContentsTheSame(
                oldItem: SearchFilterEntity.ArtCategoryFilter,
                newItem: SearchFilterEntity.ArtCategoryFilter
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
