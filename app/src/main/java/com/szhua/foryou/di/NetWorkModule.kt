package com.szhua.foryou.di

import com.szhua.foryou.api.BMobService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Singleton
    @Provides
    fun provideBMobService():BMobService{
        return  BMobService.create()
    }




}