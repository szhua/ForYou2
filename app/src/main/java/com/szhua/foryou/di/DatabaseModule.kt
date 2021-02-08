package com.szhua.foryou.di

import android.content.Context
import com.szhua.foryou.dao.BMobDiaryDao
import com.szhua.foryou.dao.FavDiaryDao
import com.szhua.foryou.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase{
          return  AppDatabase.getInstance(context)
    }

    @Provides
    fun providesFavDao(appDatabase: AppDatabase) :FavDiaryDao{
          return  appDatabase.favDiaryDao()
    }


    @Provides
    fun providesDiaryDao(appDatabase: AppDatabase):BMobDiaryDao{
        return  appDatabase.bmobDiaryDao()
    }





}