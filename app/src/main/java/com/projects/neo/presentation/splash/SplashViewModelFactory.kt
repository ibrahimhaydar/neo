package com.projects.neo.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.neo.domain.usecase.splash.GetStringsUseCase


class SplashViewModelFactory(
    private val getStringsUseCase: GetStringsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(getStringsUseCase) as T
    }
}

