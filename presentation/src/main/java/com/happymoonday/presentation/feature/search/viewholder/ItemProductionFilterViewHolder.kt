package com.happymoonday.presentation.feature.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.presentation.R
import com.happymoonday.presentation.databinding.ItemSearchFilterTypeBinding

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 재작연도 필터 아이템 ViewHolder
 * @author LeeDongHun
 *
 *
 **/
class ItemProductionFilterViewHolder(
    val binding: ItemSearchFilterTypeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchFilterEntity.ProductionYearFilter) {
        binding.tvFilterTitle.apply {
            text = item.displayName//text 적용
            val textColor = itemView.resources.getColor(
                if (item.isSelected) {
                    R.color.black
                } else {
                    R.color.gray
                }, null
            )
            setTextColor(textColor)//textcolor 적용
        }
    }
}
