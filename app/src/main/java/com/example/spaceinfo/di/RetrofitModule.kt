package com.example.spaceinfo.di

import com.example.spaceinfo.net.RoverApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
class RetrofitModule {

    @Provides
    fun provideRoverApi( retrofit: Retrofit) : RoverApi {
        return retrofit.create(RoverApi::class.java)
    }

    @Provides
    fun provideRetrofit() : Retrofit {
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS).build()
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }

}