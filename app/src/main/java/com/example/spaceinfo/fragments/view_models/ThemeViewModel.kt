package com.example.spaceinfo.fragments.view_models

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceinfo.R
import com.example.spaceinfo.models.ThemeItem
import com.example.spaceinfo.repository.NasaCardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class ThemeViewModel @ViewModelInject  constructor(
    private val nasaCardsRepository: NasaCardsRepository
) : ViewModel() {
    val isProcess = MutableLiveData<Boolean>(false)
    val msg : MutableLiveData<String>
    var heartIconResource = MutableLiveData<Int>()
    var loadingStrategy : LoadingStrategy = Internet
        set(value){
            field = value
            heartIconResource.value = if ( loadingStrategy == Internet) R.drawable.ic_favorite_empty else R.drawable.ic_favorite
        }

    var cardLiveData : LiveData<MutableList<ThemeItem>> = MutableLiveData(mutableListOf())

    init {
        this.cardLiveData = nasaCardsRepository.themes
        this.msg = nasaCardsRepository.log
        heartIconResource.value = R.drawable.ic_favorite_empty
        cardLiveData.observeForever { isProcess.value = false }
        updateThemes()
    }

    fun updateThemes() = viewModelScope.launch( Dispatchers.IO ) {
        isProcess.postValue(true)
        nasaCardsRepository.getCards( loadingStrategy )

    }

}