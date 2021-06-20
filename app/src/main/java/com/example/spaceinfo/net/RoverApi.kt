package com.example.spaceinfo.net

import com.example.spaceinfo.models.ThemeItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RoverApi {
    companion object{
        const val apiKey = "yJqsf3ke1ChNQhtRXAfvacnY63Wrg3jwny74I4VS"
    }

    @GET("planetary/apod")
    fun getMarsPhotos(  @Query("count") count : Int,
                        @Query("api_key") key: String = apiKey) : Call<MutableList<ThemeItem>>


}