package com.happymoonday.presentation.feature.search.fragment.dialog.bottom

import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseBottomSheetDialogFragment
import com.happymoonday.presentation.databinding.FragmentBottomDialogProductionYearFilterBinding
import com.happymoonday.presentation.feature.search.adapter.ProductionYearFilterRvAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 제작년도 필터를 보여주는 BottomDialogFragment
 * @author LeeDongHun
 *
 *
 **/
@AndroidEntryPoint
class ProductionYearFilterBottomDialogFragment :
    BaseBottomSheetDialogFragment<FragmentBottomDialogProductionYearFilterBinding>(R.layout.fragment_bottom_dialog_production_year_filter) {

    companion object {
        private lateinit var productionYearFilterList: List<SearchFilterEntity.ProductionYearFilter>
        private lateinit var productionYearFilterClicked: (SearchFilterEntity.ProductionYearFilter) -> Unit
        fun getInstance(
            productionYearFilterList: List<SearchFilterEntity.ProductionYearFilter>,
            productionYearFilterClicked: (SearchFilterEntity.ProductionYearFilter) -> Unit
        ): ProductionYearFilterBottomDialogFragment {
            this.productionYearFilterClicked = productionYearFilterClicked
            this.productionYearFilterList = productionYearFilterList
            return ProductionYearFilterBottomDialogFragment()
        }
    }

    private lateinit var productionYearFilterAdapter: ProductionYearFilterRvAdapter

    override fun FragmentBottomDialogProductionYearFilterBinding.onCreateView() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }

    private fun initSet() {
        productionYearFilterAdapter = ProductionYearFilterRvAdapter()
        binding.rvProductionYearFilterList.apply {
            adapter = productionYearFilterAdapter
        }
        productionYearFilterAdapter.submitList(productionYearFilterList)
    }

    private fun setListenerEvent() {
        productionYearFilterAdapter.setItemClickListener(object : ProductionYearFilterRvAdapter.ItemClickListener {
            override fun onClickItem(item: SearchFilterEntity.ProductionYearFilter) {
                productionYearFilterClicked(item)
                dismiss()
            }
        })
    }

    private fun getDataFromVm() {

    }
}
