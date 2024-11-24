package com.happymoonday.presentation.feature.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.happymoonday.presentation.R
import com.happymoonday.presentation.databinding.ItemSearchedArtListBinding
import com.happymoonday.presentation.feature.search.viewholder.ItemSearchArtListViewHolder
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel

/**
 * Create Date: 2024. 11. 25.
 *
 * 검색화면 recyclerview 어댑터
 * @author LeeDongHun
 *
 *
**/
class SearchItemRvAdapter : ListAdapter<SemaPsgudInfoKorInfoRowUiModel,RecyclerView.ViewHolder>(diffUtil){

    private lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onClickItem(item: SemaPsgudInfoKorInfoRowUiModel)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemSearchedArtListBinding :ItemSearchedArtListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_searched_art_list,
                parent,
                false
            )
        return ItemSearchArtListViewHolder(itemSearchedArtListBinding).apply {
            itemView.setOnClickListener {
                itemClickListener.onClickItem(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemSearchArtListViewHolder).apply {
            bind(currentList[bindingAdapterPosition])
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SemaPsgudInfoKorInfoRowUiModel>() {
            override fun areItemsTheSame(
                oldItem: SemaPsgudInfoKorInfoRowUiModel,
                newItem: SemaPsgudInfoKorInfoRowUiModel
            ): Boolean {
                return oldItem.productName == newItem.productName && oldItem.writerName == newItem.writerName
            }
            override fun areContentsTheSame(
                oldItem: SemaPsgudInfoKorInfoRowUiModel,
                newItem: SemaPsgudInfoKorInfoRowUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
