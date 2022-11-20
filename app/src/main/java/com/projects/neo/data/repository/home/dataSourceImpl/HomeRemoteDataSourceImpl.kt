package com.projects.neo.data.repository.home.dataSourceImpl

import com.projects.neo.data.api.ApiService
import com.projects.neo.data.model.*
import com.projects.neo.data.repository.home.dataSource.HomeRemoteDataSource
import com.projects.neo.data.repository.splash.dataSource.SplashRemoteDataSource
import retrofit2.Response

class HomeRemoteDataSourceImpl(private val apiService: ApiService):HomeRemoteDataSource{
    override suspend fun getPortfolios(): Response<ResponsePortfolios> {
        return apiService.getPortfolios()
    }
}