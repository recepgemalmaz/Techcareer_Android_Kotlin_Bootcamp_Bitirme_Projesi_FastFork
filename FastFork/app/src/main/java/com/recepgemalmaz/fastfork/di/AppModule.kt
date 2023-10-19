package com.recepgemalmaz.fastfork.di

import com.recepgemalmaz.fastfork.data.repo.YemeklerDaoRepository
import com.recepgemalmaz.fastfork.retrofit.ApiUtils
import com.recepgemalmaz.fastfork.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerDaoRepository(ydao: YemeklerDao) : YemeklerDaoRepository{
        return YemeklerDaoRepository(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }
}