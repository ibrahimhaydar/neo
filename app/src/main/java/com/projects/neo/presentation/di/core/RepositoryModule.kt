package com.projects.neo.presentation.di.core


import com.projects.neo.data.repository.historical.HistoricalRepositoryImpl
import com.projects.neo.data.repository.historical.dataSource.HistoricalRemoteDataSource
import com.projects.neo.data.repository.historical.dataSourceImpl.HistoricalRemoteDataSourceImpl
import com.projects.neo.data.repository.home.HomeRepositoryImpl
import com.projects.neo.data.repository.home.dataSource.HomeRemoteDataSource
import com.projects.neo.data.repository.splash.SplashRepositoryImpl
import com.projects.neo.data.repository.splash.dataSource.SplashRemoteDataSource
import com.projects.neo.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSplashRepository(
        splashRemoteDataSource: SplashRemoteDataSource
    ): SplashRepository {
        return SplashRepositoryImpl(
            splashRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeRemoteDataSource: HomeRemoteDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(
            homeRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideHistoricalRepository(
        historicalRemoteDataSource: HistoricalRemoteDataSource
    ): HistoricalRepository {
        return HistoricalRepositoryImpl(
            historicalRemoteDataSource
        )
    }
}