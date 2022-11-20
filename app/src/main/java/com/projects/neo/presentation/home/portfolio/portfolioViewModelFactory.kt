package com.projects.neo.presentation.home.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.neo.domain.usecase.portfolio.GetPortfoliosUseCase

class portfolioViewModelFactory(
    private val getPortfoliosUseCase: GetPortfoliosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PortfolioViewModel(getPortfoliosUseCase) as T
    }
}
