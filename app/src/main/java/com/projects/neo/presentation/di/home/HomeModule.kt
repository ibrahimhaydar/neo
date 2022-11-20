package com.projects.neo.presentation.di.home


import com.projects.neo.domain.usecase.portfolio.GetPortfoliosUseCase
import com.projects.neo.domain.usecase.splash.GetStringsUseCase
import com.projects.neo.presentation.home.portfolio.portfolioViewModelFactory
import com.projects.neo.presentation.splash.SplashViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class HomeModule {
    @ActivityScoped
    @Provides
    fun provideHomeViewModelFactory(
        getPortfoliosUseCase: GetPortfoliosUseCase
    ): portfolioViewModelFactory {
        return portfolioViewModelFactory(
            getPortfoliosUseCase
        )
    }

}