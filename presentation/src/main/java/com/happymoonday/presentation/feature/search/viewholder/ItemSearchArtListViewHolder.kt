package com.happymoonday.presentation.feature.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.happymoonday.presentation.R
import com.happymoonday.presentation.databinding.ItemSearchedArtListBinding
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel

/**
 * Create Date: 2024. 11. 25.
 *
 * 검색화면 아트 리스트 뷰홀더
 * @author LeeDongHun
 *
 *
**/
class ItemSearchArtListViewHolder(
    val binding:ItemSearchedArtListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SemaPsgudInfoKorInfoRowUiModel) {
        binding.tvArtTitle.text = item.productName
        binding.tvWriterMadeYear.text = buildString {
            append(item.writerName)
            append("(${item.dateOfMade})")
        }
        binding.tvCategory.text = item.productCategory

        Glide.with(itemView.context)
            .load(item.thumbNailImage)
            .placeholder(R.drawable.icon_home)
            .error(R.drawable.icon_home)
            .into(binding.ivArtThumbnail)

    }
}
