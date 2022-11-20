package com.projects.neo.presentation.di.splash


import com.projects.neo.domain.usecase.splash.GetStringsUseCase
import com.projects.neo.presentation.splash.SplashViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class SplashModule {
    @ActivityScoped
    @Provides
    fun provideSplashViewModelFactory(
        getStringsUseCase: GetStringsUseCase
    ): SplashViewModelFactory {
        return SplashViewModelFactory(
            getStringsUseCase
        )
    }

}