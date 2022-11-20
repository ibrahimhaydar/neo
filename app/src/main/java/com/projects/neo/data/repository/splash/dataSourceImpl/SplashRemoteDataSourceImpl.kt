package com.projects.neo.data.repository.splash.dataSourceImpl

import com.projects.neo.data.api.ApiService
import com.projects.neo.data.model.*
import com.projects.neo.data.repository.splash.dataSource.SplashRemoteDataSource
import retrofit2.Response

class SplashRemoteDataSourceImpl(private val apiService: ApiService):SplashRemoteDataSource{
    override suspend fun getStrings(): Response<ResponseStrings> {
        return apiService.getStrings()
    }
}