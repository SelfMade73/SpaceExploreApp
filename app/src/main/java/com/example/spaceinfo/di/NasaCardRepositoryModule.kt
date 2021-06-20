package com.example.spaceinfo.di

import com.example.spaceinfo.repository.NasaCardRepositoryImpl
import com.example.spaceinfo.repository.NasaCardsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
abstract class NasaCardRepositoryModule {
    @Binds
    abstract fun providesNasaCardsRepository( impl : NasaCardRepositoryImpl ) : NasaCardsRepository
}