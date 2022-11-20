package com.projects.neo.presentation.home.portfolio_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.neo.domain.usecase.historical.GetHistoricalUseCase
import com.projects.neo.domain.usecase.portfolio.GetPortfoliosUseCase

class portfolioDetailsViewModelFactory(
    private val getHistoricalUseCase: GetHistoricalUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PortfolioDetailsViewModel(getHistoricalUseCase) as T
    }
}
