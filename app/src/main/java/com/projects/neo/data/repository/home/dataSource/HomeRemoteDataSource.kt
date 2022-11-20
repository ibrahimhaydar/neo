package com.projects.neo.data.repository.home.dataSource

import com.projects.neo.data.model.ResponsePortfolios
import com.projects.neo.data.model.ResponseStrings
import retrofit2.Response

interface HomeRemoteDataSource {
    suspend fun getPortfolios(): Response<ResponsePortfolios>

}