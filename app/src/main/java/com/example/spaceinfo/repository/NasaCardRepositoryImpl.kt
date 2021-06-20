package com.example.spaceinfo.repository

import androidx.lifecycle.MutableLiveData
import com.example.spaceinfo.fragments.view_models.Internet
import com.example.spaceinfo.fragments.view_models.LoadingStrategy
import com.example.spaceinfo.fragments.view_models.LocalStorage
import com.example.spaceinfo.net.RoverApi
import com.example.spaceinfo.database.AppDatabase
import com.example.spaceinfo.models.ThemeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NasaCardRepositoryImpl @Inject constructor(
        private val database: AppDatabase,
        private val roverApi: RoverApi,
) : NasaCardsRepository{
    override val themes = MutableLiveData<MutableList<ThemeItem>>(mutableListOf());
    override val log = MutableLiveData<String>()

    override suspend fun getCards( loadingStrategy: LoadingStrategy) {
        when( loadingStrategy ){
            is Internet ->  getCardsFromNet()
            is LocalStorage -> getCardsFromDB()
        }

    }

    override suspend fun saveCardToDatabase(card : ThemeItem ) =  database.nasaCardDao().saveCard( card )

    override suspend fun isItemExists ( id : Long ) : Boolean = database.nasaCardDao().isItemExists( id )

    override suspend fun deleteCardById(id: Long) = database.nasaCardDao().deleteCardById( id )

    private fun getCardsFromNet() {
        roverApi.getMarsPhotos(15).enqueue(object : Callback<MutableList<ThemeItem>>{
            override fun onFailure(call: Call<MutableList<ThemeItem>>, t: Throwable) {
                themes.postValue(themes.value)
                log.postValue(t.message)
            }

            override fun onResponse(call: Call<MutableList<ThemeItem>>, response: Response<MutableList<ThemeItem>>) {
                if ( response.isSuccessful){
                    response.body()?.let {
                        themes.value?.let { cards -> it += cards }
                        themes.postValue(it) }
                    }
                }
        })
    }

    private suspend fun getCardsFromDB() {
        val cards =  database.nasaCardDao().getAllCards()
        cards.reverse()
        themes.postValue( cards )
    }
}