package com.projects.neo.presentation.di.login


import com.projects.neo.domain.usecase.splash.GetStringsUseCase
import com.projects.neo.presentation.login.fragments.LoginViewModelFactory
import com.projects.neo.presentation.splash.SplashViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class LoginModule {
    @ActivityScoped
    @Provides
    fun provideLoginViewModelFactory(
    ): LoginViewModelFactory {
        return LoginViewModelFactory()
    }

}