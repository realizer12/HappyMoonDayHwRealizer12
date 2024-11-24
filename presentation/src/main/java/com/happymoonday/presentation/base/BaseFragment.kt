package com.happymoonday.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Create Date: 2024. 11. 24.
 *
 * 베아스가 되는 프래그먼트 클래스
 * @author LeeDongHun
 *
**/
abstract class BaseFragment<VDB: ViewDataBinding>(
    @LayoutRes val layoutRes: Int
) : Fragment(){
    protected lateinit var binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.onCreateView()
        return binding.root
    }

    abstract fun VDB.onCreateView()

    fun showToast(msg:String){
        Toast.makeText(requireActivity(),msg, Toast.LENGTH_SHORT).show()
    }
}
