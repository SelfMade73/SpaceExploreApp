package com.example.spaceinfo.repository

import androidx.lifecycle.MutableLiveData
import com.example.spaceinfo.fragments.view_models.LoadingStrategy
import com.example.spaceinfo.models.ThemeItem

interface NasaCardsRepository {
    val themes : MutableLiveData<MutableList<ThemeItem>>
    val log : MutableLiveData<String>
    suspend fun getCards(loadingStrategy: LoadingStrategy)
    suspend fun saveCardToDatabase(card : ThemeItem ) : Long
    suspend fun isItemExists ( id : Long ) : Boolean
    suspend fun deleteCardById( id : Long )
}