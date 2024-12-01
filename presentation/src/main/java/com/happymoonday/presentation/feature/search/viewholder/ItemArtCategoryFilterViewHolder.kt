package com.happymoonday.presentation.feature.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.presentation.R
import com.happymoonday.presentation.databinding.ItemSearchFilterTypeWithSelectorBinding

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 예술 카테고리 필터 아이템 viewHolder
 *
 * @author LeeDongHun
 *
 *
**/
class ItemArtCategoryFilterViewHolder(
    val binding:ItemSearchFilterTypeWithSelectorBinding
):RecyclerView.ViewHolder(binding.root){

    fun bind(item: SearchFilterEntity.ArtCategoryFilter){
        if(item.isSelected) {
            binding.cbFilterCheck.isChecked = true
        }
        binding.tvFilterTitle.apply {
            text = item.displayName
            val textColor = itemView.resources.getColor(
                if(item.isSelected){
                    R.color.black
                }else{
                    R.color.gray
                },null
            )
            setTextColor(textColor)
        }
    }
}
