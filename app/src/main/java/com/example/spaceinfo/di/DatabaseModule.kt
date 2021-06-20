package com.example.spaceinfo.di

import android.content.Context
import androidx.room.Room
import com.example.spaceinfo.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppDataBase(@ApplicationContext context : Context ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.databaseName
        ).build()
    }


}