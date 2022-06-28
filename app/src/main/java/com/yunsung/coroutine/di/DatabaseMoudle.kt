package com.yunsung.coroutine.di

import android.content.Context
import androidx.room.Room
import com.yunsung.coroutine.data.naverdata.local.NaverDatabase
import com.yunsung.coroutine.util.Utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


    // database dao 구성

@Module
@InstallIn(SingletonComponent::class)
object DatabaseMoudle {
    @Provides
    @Singleton
    fun provideMealDao(naverDatabase : NaverDatabase) = naverDatabase.naverDao()


    // database 구성

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NaverDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}