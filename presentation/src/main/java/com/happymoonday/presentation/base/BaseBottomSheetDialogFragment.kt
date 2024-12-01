package com.happymoonday.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Create Date: 2024. 12. 1.
 *
 * 베이스가 되는  bottom sheet dialog fragment
 * @author LeeDongHun
 *
 *
**/
abstract class BaseBottomSheetDialogFragment <VDB : ViewDataBinding>(@LayoutRes val layoutRes: Int) : BottomSheetDialogFragment() {
    lateinit var binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        isCancelable = false
        binding.onCreateView()
        return binding.root
    }

    abstract fun VDB.onCreateView()
}
