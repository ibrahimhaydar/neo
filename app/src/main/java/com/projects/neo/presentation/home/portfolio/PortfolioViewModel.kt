package com.projects.neo.presentation.home.portfolio

import com.projects.neo.data.util.Resource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.projects.neo.data.util.AppConstants
import com.projects.neo.data.util.AppHelper
import com.projects.neo.domain.usecase.portfolio.GetPortfoliosUseCase
import com.projects.neo.presentation.App

class PortfolioViewModel(private val getPortfoliosUseCase: GetPortfoliosUseCase
): ViewModel() {

    fun getPortfolios() = liveData {
        if(AppHelper.isNetworkAvailable(App.instance)){
            val getData = getPortfoliosUseCase.execute()
            emit(getData)
        }else{
            emit(Resource.Error(AppConstants.NO_INTERNET))
        }
    }


}


