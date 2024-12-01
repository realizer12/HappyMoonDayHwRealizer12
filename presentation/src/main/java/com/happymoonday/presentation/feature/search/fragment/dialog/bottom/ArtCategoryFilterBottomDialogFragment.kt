package com.happymoonday.presentation.feature.search.fragment.dialog.bottom

import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.domain.model.SearchFilterType
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseBottomSheetDialogFragment
import com.happymoonday.presentation.databinding.FragmentBottomDialogArtCategoryFilterBinding
import com.happymoonday.presentation.feature.search.adapter.ArtCategoryFilterRvAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 예술 카테고리 필터를 보여주는 BottomDialogFragment
 *
 * @author LeeDongHun
 *
 *
 **/
@AndroidEntryPoint
class ArtCategoryFilterBottomDialogFragment :
    BaseBottomSheetDialogFragment<FragmentBottomDialogArtCategoryFilterBinding>(R.layout.fragment_bottom_dialog_art_category_filter) {

    companion object {
        private lateinit var artCategoryFilterList: List<SearchFilterEntity.ArtCategoryFilter>
        private lateinit var artCategoryFilterClicked: (changedArtCategoryFilterList: List<SearchFilterEntity.ArtCategoryFilter>) -> Unit
        fun getInstance(
            artCategoryFilterList: List<SearchFilterEntity.ArtCategoryFilter>,
            artCategoryFilterClicked: (changedArtCategoryFilterList: List<SearchFilterEntity.ArtCategoryFilter>) -> Unit
        ): ArtCategoryFilterBottomDialogFragment {
            this.artCategoryFilterClicked = artCategoryFilterClicked
            //UNKNOWN은 제외
            this.artCategoryFilterList =
                artCategoryFilterList.filterNot { it.filterType == SearchFilterType.ArtCategory.Unknown }
            return ArtCategoryFilterBottomDialogFragment()
        }
    }

    private lateinit var artCategoryFilterAdapter: ArtCategoryFilterRvAdapter

    override fun FragmentBottomDialogArtCategoryFilterBinding.onCreateView() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }

    private fun initSet() {
        artCategoryFilterAdapter = ArtCategoryFilterRvAdapter()
        binding.rvArtCategoryFilterList.apply {
            adapter = artCategoryFilterAdapter
        }
        artCategoryFilterAdapter.submitList(artCategoryFilterList)
    }

    private fun setListenerEvent() {
        artCategoryFilterAdapter.setItemClickListener(object :
            ArtCategoryFilterRvAdapter.ItemClickListener {
            override fun onCheckBoxClicked(
                item: SearchFilterEntity.ArtCategoryFilter,
                isChecked: Boolean
            ) {
                artCategoryFilterList.find { it.filterType == item.filterType }
                    ?.let { it.isSelected = isChecked }
                binding.rvArtCategoryFilterList.post {
                    artCategoryFilterAdapter.submitList(artCategoryFilterList)
                }
            }
        })

        //확인 버튼 클릭시
        binding.btnConfirm.setOnClickListener {
            artCategoryFilterClicked(artCategoryFilterList)
            dismiss()
        }
    }

    private fun getDataFromVm() {

    }
}
