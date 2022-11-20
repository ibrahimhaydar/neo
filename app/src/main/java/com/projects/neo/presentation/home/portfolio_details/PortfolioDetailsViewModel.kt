package com.projects.neo.presentation.home.portfolio_details

import com.projects.neo.data.util.Resource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.projects.neo.data.util.AppConstants
import com.projects.neo.data.util.AppHelper
import com.projects.neo.domain.usecase.historical.GetHistoricalUseCase
import com.projects.neo.domain.usecase.portfolio.GetPortfoliosUseCase
import com.projects.neo.presentation.App

class PortfolioDetailsViewModel(private val getHistoricalUseCase: GetHistoricalUseCase
): ViewModel() {

    fun getHistorical() = liveData {
        if(AppHelper.isNetworkAvailable(App.instance)){
            val getData = getHistoricalUseCase.execute()
            emit(getData)
        }else{
            emit(Resource.Error(AppConstants.NO_INTERNET))
        }
    }


}


