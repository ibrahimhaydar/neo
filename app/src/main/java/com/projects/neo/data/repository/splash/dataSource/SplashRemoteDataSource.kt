package com.projects.neo.data.repository.splash.dataSource

import com.projects.neo.data.model.ResponseStrings
import retrofit2.Response

interface SplashRemoteDataSource {
    suspend fun getStrings(): Response<ResponseStrings>

}