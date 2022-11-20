package com.projects.neo.presentation.di.core

import com.projects.neo.data.api.ApiService
import com.projects.neo.data.repository.historical.dataSource.HistoricalRemoteDataSource
import com.projects.neo.data.repository.historical.dataSourceImpl.HistoricalRemoteDataSourceImpl
import com.projects.neo.data.repository.home.dataSource.HomeRemoteDataSource
import com.projects.neo.data.repository.home.dataSourceImpl.HomeRemoteDataSourceImpl
import com.projects.neo.data.repository.splash.dataSource.SplashRemoteDataSource
import com.projects.neo.data.repository.splash.dataSourceImpl.SplashRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule() {
    @Singleton
    @Provides
    fun provideSplashDataSources(apiService: ApiService): SplashRemoteDataSource {
        return SplashRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideHomeDataSources(apiService: ApiService): HomeRemoteDataSource {
        return HomeRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideHistoricalDataSources(apiService: ApiService): HistoricalRemoteDataSource {
        return HistoricalRemoteDataSourceImpl(apiService)
    }
}