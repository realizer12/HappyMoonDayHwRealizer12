package com.happymoonday.presentation.feature.search.fragment.dialog.bottom

import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseBottomSheetDialogFragment
import com.happymoonday.presentation.databinding.FragmentBottomDialogArtCategoryFilterBinding
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
class ArtCategoryFilterBottomDialogFragment:BaseBottomSheetDialogFragment<FragmentBottomDialogArtCategoryFilterBinding>(
    R.layout.fragment_bottom_dialog_art_category_filter) {

    companion object{

        fun getInstance(

        ):ArtCategoryFilterBottomDialogFragment{
            return ArtCategoryFilterBottomDialogFragment()
        }
    }

    override fun FragmentBottomDialogArtCategoryFilterBinding.onCreateView() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }
    private fun initSet(){

    }
    private fun setListenerEvent(){

    }
    private fun getDataFromVm(){

    }
}
