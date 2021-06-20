package com.example.spaceinfo.fragments.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceinfo.R
import com.example.spaceinfo.models.IntroItem

class IntroViewModel : ViewModel() {

    val introItems = listOf(
            IntroItem(R.raw.outer_space,R.string.title_1, R.string.descr_1),
            IntroItem(R.raw.rocket_launching,R.string.title_2, R.string.descr_2),
            IntroItem(R.raw.space_tour,R.string.title_3, R.string.descr_3)
    )

    val nextBtnText = MutableLiveData<Int>(R.string.next_btn)
    val currentPage = MutableLiveData(0)

    fun onNextBtnClick(){
        currentPage.value = currentPage.value?.inc()
        nextBtnText.value = if (currentPage.value!! < introItems.size - 1 ){ R.string.next_btn }else{ R.string.next_btn_start}
        if ( currentPage.value!! == introItems.size ){ currentPage.value?.minus(1) }
    }

    fun onBackBtnClick(){
        if ( currentPage.value!! > 0 ){
            currentPage.value = currentPage.value?.dec()
            nextBtnText.value = R.string.next_btn
        }
    }


}