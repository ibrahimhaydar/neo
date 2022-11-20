package com.projects.neo.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.projects.neo.data.util.AppConstants.NO_INTERNET
import com.projects.neo.data.util.AppHelper.Companion.isNetworkAvailable
import com.projects.neo.data.util.Resource
import com.projects.neo.domain.usecase.splash.GetStringsUseCase
import com.projects.neo.presentation.App

class SplashViewModel(
    private val getStringsUseCase: GetStringsUseCase
): ViewModel() {
    fun getStrings() = liveData {
        if(isNetworkAvailable(App.instance)){
            val getStringsData = getStringsUseCase.execute()
            emit(getStringsData)
        }else{
            emit(Resource.Error(NO_INTERNET))
        }
    }


}


