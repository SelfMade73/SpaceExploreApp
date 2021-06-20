package com.example.spaceinfo.fragments.view_models

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceinfo.R
import com.example.spaceinfo.models.ThemeItem
import com.example.spaceinfo.repository.NasaCardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NasaInfoViewModel @ViewModelInject constructor(
        private val nasaCardsRepository: NasaCardsRepository
)  : ViewModel() {
    var item : ThemeItem? = null
        set(value) {
            field = value
            updateSaveIcon()
        }


    val imgRes = MutableLiveData<Int>()

    fun updateSaveIcon()  =
        viewModelScope.launch(Dispatchers.IO){
            val isItemExists = nasaCardsRepository.isItemExists( item!!.id )
            withContext(Dispatchers.Main){
                imgRes.value = if ( isItemExists ) R.drawable.ic_favorite else R.drawable.ic_favorite_empty
            }
        }


    fun saveOrDeleteCard() = viewModelScope.launch {
        val exist = nasaCardsRepository.isItemExists( item!!.id )
        if ( exist ){
            nasaCardsRepository.deleteCardById(item!!.id)
        } else {
            item!!.id = nasaCardsRepository.saveCardToDatabase(item!!)
        }

    }
}