package com.projects.neo.presentation.di.core

import com.projects.neo.domain.repository.*
import com.projects.neo.domain.usecase.historical.GetHistoricalUseCase
import com.projects.neo.domain.usecase.portfolio.GetPortfoliosUseCase
import com.projects.neo.domain.usecase.splash.GetStringsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGeStringUseCase(splashRepository: SplashRepository): GetStringsUseCase {
        return GetStringsUseCase(splashRepository)
    }

    @Provides
    fun provideGetPortfolioUseCase(homeRepository: HomeRepository): GetPortfoliosUseCase {
        return GetPortfoliosUseCase(homeRepository)
    }

    @Provides
    fun provideGetHistoricalUseCase(historicalRepository: HistoricalRepository): GetHistoricalUseCase {
        return GetHistoricalUseCase(historicalRepository)
    }
}