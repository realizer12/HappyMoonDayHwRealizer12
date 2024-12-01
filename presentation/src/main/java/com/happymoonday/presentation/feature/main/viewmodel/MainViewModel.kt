package com.happymoonday.presentation.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.happymoonday.presentation.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel(){

    //즐겨찾기 화면으로 이동
    private val _moveToFavoriteFragment = MutableLiveData<SingleEvent<Unit>>()
    val moveToFavoriteFragment: LiveData<SingleEvent<Unit>> = _moveToFavoriteFragment

    fun moveToFavoriteFragment(){
        _moveToFavoriteFragment.value = SingleEvent(Unit)
    }
}
